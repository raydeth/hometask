package com.github.raydeth.update;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.github.raydeth.update.model.Product;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UpdateProduct implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    private static final String TABLE_NAME = "product";

    private static final AmazonDynamoDB DYNAMO_DB_CLIENT = AmazonDynamoDBClientBuilder.defaultClient();

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent httpRequest, Context context) {
        Gson gson = new Gson();
        context.getLogger().log("Body: " + httpRequest.getBody());
        Product product = gson.fromJson(httpRequest.getBody(), Product.class);

        Optional<Product> existedProduct = findExistedProduct(product.getId());
        existedProduct.orElseThrow(() -> new IllegalStateException("Can not find product by id: " + product.getId()));

        Map<String, AttributeValue> itemKey = new HashMap<>();
        itemKey.put("id", getAttributeValue(product.getId().toString()));

        Map<String, AttributeValueUpdate> itemValues = getStringAttributeValueUpdateMap(product);

        UpdateItemRequest request = new UpdateItemRequest()
                .withTableName(TABLE_NAME)
                .withKey(itemKey)
                .withAttributeUpdates(itemValues);

        DYNAMO_DB_CLIENT.updateItem(request);
        APIGatewayV2HTTPResponse response = new APIGatewayV2HTTPResponse();
        response.setStatusCode(200);
        return response;
    }

    private Map<String, AttributeValueUpdate> getStringAttributeValueUpdateMap(Product product) {
        Map<String, AttributeValueUpdate> itemValues = new HashMap<>();
        itemValues.put("name", getAttributeValueUpdate(getAttributeValue(product.getName())));
        itemValues.put("url", getAttributeValueUpdate(getAttributeValue(product.getUrl())));
        itemValues.put("price", getAttributeValueUpdate(getAttributeValue(product.getPrice())));
        return itemValues;
    }

    private AttributeValue getAttributeValue(String value) {
        return new AttributeValue().withS(value);
    }

    private AttributeValue getAttributeValue(Long value) {
        return new AttributeValue().withN(value.toString());
    }

    private AttributeValueUpdate getAttributeValueUpdate(AttributeValue value) {
        return new AttributeValueUpdate().withValue(value);
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
