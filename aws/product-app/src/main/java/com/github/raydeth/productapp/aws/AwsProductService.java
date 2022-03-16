package com.github.raydeth.productapp.aws;

import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;
import com.github.raydeth.productapp.aws.model.PostUploadProductRequest;
import com.github.raydeth.productapp.aws.model.PostUploadProductResult;
import com.github.raydeth.productapp.aws.model.PutUploadProductRequest;
import com.github.raydeth.productapp.aws.model.PutUploadProductResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AwsProductService {

    private Product productSdk;

    public void upload(com.github.raydeth.productapp.aws.model.Product product) {
        PostUploadProductResult postUploadProductResult = productSdk.postUploadProduct(new PostUploadProductRequest()
                .product(product));

        if (postUploadProductResult.sdkResponseMetadata().httpStatusCode() == 200) {
            System.out.println("Successfully uploaded");
        }
        return;
    }

    public void update(com.github.raydeth.productapp.aws.model.Product product) {
        PutUploadProductResult putUploadProductResult = productSdk.putUploadProduct(new PutUploadProductRequest()
                .product(product));
        if (putUploadProductResult.sdkResponseMetadata().httpStatusCode() == 200) {
            System.out.println("Successfully update");
        }
        return;
    }

    @PostConstruct
    public void postConstruct() {
        productSdk = new ProductClientBuilder()
                .connectionConfiguration(
                        new ConnectionConfiguration()
                                .maxConnections(100)
                                .connectionMaxIdleMillis(1000))
                .timeoutConfiguration(
                        new TimeoutConfiguration()
                                .httpRequestTimeout(30000)
                                .totalExecutionTimeout(100000)
                                .socketTimeout(20000))
                .build();
    }
}
