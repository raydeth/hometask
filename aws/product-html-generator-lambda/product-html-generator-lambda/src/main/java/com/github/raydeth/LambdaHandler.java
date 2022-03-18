package com.github.raydeth;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.models.dynamodb.AttributeValue;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaHandler implements RequestHandler<DynamodbEvent, String> {

    private final static AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion("us-east-1").build();

    private static final String BUCKET_NAME = "product-html-ms";

    @SneakyThrows
    @Override
    public String handleRequest(DynamodbEvent dynamodbEvent, Context context) {
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(LambdaHandler.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        List<Map<String, AttributeValue>> products = dynamodbEvent.getRecords().stream().map(r -> r.getDynamodb().getNewImage()).collect(Collectors.toList());
        for (Map<String, AttributeValue> p : products) {
            String id = p.get("id").getS();
            String name = p.get("name").getS();
            String url = p.get("url").getS();
            String price = p.get("price").getN();

            Template template = cfg.getTemplate("product.ftl");

            Map<String, Object> templateData = new HashMap<>();
            templateData.put("id", id);
            templateData.put("name", name);
            templateData.put("url", url);
            templateData.put("price", price);

            StringWriter out = new StringWriter();

            template.process(templateData, out);

            byte[] productHtmlBytes = out.getBuffer().toString().getBytes(StandardCharsets.UTF_8);
            out.flush();

            String fileName = id + ".html";
            InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(BUCKET_NAME, fileName);
            InitiateMultipartUploadResult initResponse = amazonS3.initiateMultipartUpload(initRequest);

            List<PartETag> partETags = new ArrayList<>();

            ByteArrayOutputStream bufferOutputStream = new ByteArrayOutputStream();

            bufferOutputStream.write(productHtmlBytes);

            ByteArrayInputStream byteFilePart = new ByteArrayInputStream(bufferOutputStream.toByteArray());
            UploadPartRequest uploadRequest = buildUploadPartRequest(fileName, initResponse, 1, byteFilePart)
                    .withLastPart(true);
            sendPartUpload(partETags, uploadRequest);

            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(BUCKET_NAME, fileName,
                    initResponse.getUploadId(), partETags);
            CompleteMultipartUploadResult result = amazonS3.completeMultipartUpload(compRequest);
        }

        return null;
    }

    private void sendPartUpload(List<PartETag> partETags, UploadPartRequest uploadRequest) {
        UploadPartResult uploadResult = amazonS3.uploadPart(uploadRequest);

        partETags.add(uploadResult.getPartETag());
    }

    private UploadPartRequest buildUploadPartRequest(String fileName, InitiateMultipartUploadResult initResponse, int partNumber, ByteArrayInputStream byteFilePart) {
        return new UploadPartRequest()
                .withBucketName(BUCKET_NAME)
                .withKey(fileName)
                .withUploadId(initResponse.getUploadId())
                .withPartNumber(partNumber)
                .withPartSize(byteFilePart.available())
                .withInputStream(byteFilePart);
    }
}
