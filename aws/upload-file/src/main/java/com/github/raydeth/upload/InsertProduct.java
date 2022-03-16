package com.github.raydeth.upload;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.github.raydeth.upload.model.Product;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InsertProduct implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    private static final String TABLE_NAME = "product";

    private static final AmazonDynamoDB DYNAMO_DB_CLIENT = AmazonDynamoDBClientBuilder.defaultClient();

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent httpRequest, Context context) {
        context.getLogger().log("321");
        Gson gson = new Gson();
        context.getLogger().log("Body: " + httpRequest.getBody());
        Product product = gson.fromJson(httpRequest.getBody(), Product.class);

        Optional<Product> existedProduct = findExistedProduct(product.getId());
        if (existedProduct.isEmpty()) {
            Map<String, AttributeValue> itemValues = getStringAttributeValueMap(product);

            PutItemRequest request = new PutItemRequest()
                    .withTableName(TABLE_NAME)
                    .withItem(itemValues);
            context.getLogger().log("123");
            DYNAMO_DB_CLIENT.putItem(request);
        }
        APIGatewayV2HTTPResponse response = new APIGatewayV2HTTPResponse();
        response.setStatusCode(200);
        return response;
    }

    private Map<String, AttributeValue> getStringAttributeValueMap(Product product) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        UUID productId = product.getId() == null ? UUID.randomUUID() : product.getId();
        itemValues.put("id", getAttributeValue(productId.toString()));
        itemValues.put("name", getAttributeValue(product.getName()));
        itemValues.put("url", getAttributeValue(product.getUrl()));
        itemValues.put("price", getAttributeValue(product.getPrice()));
        return itemValues;
    }

    private AttributeValue getAttributeValue(String value) {
        return new AttributeValue().withS(value);
    }

    private AttributeValue getAttributeValue(Long value) {
        return new AttributeValue().withN(value.toString());
    }

    private Optional<Product> findExistedProduct(UUID productId) {
        if (productId == null) {
            return Optional.empty();
        }

        HashMap<String, AttributeValue> keyToGet =
                new HashMap<>();

        keyToGet.put("id", new AttributeValue()
                .withS(productId.toString()));

        GetItemRequest request = new GetItemRequest()
                .withKey(keyToGet)
                .withTableName(TABLE_NAME);

        GetItemResult existedItem = DYNAMO_DB_CLIENT.getItem(request);
        if (existedItem == null || existedItem.getItem() == null) {
            return Optional.empty();
        }
        Map<String, AttributeValue> returnedItem = existedItem.getItem();

        UUID id = UUID.fromString(returnedItem.get("id").getS());
        String name = returnedItem.get("name").getS();
        String url = returnedItem.get("url").getS();
        long price = Long.parseLong(returnedItem.get("price").getN());
        return Optional.of(new Product(id, name, url, price));
    }
}
