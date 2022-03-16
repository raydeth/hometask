/**

*/
package com.github.raydeth.productapp.aws.model.transform;


import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import com.github.raydeth.productapp.aws.model.Product;

import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * Product JSON Unmarshaller
 */
public class ProductJsonUnmarshaller implements Unmarshaller<Product, JsonUnmarshallerContext> {

    public Product unmarshall(JsonUnmarshallerContext context) throws Exception {
        Product product = new Product();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return null;
        }

        while (true) {
            if (token == null)
                break;

            if (token == FIELD_NAME || token == START_OBJECT) {
                if (context.testExpression("id", targetDepth)) {
                    context.nextToken();
                    product.setId(context.getUnmarshaller(String.class).unmarshall(context));
                }
                if (context.testExpression("name", targetDepth)) {
                    context.nextToken();
                    product.setName(context.getUnmarshaller(String.class).unmarshall(context));
                }
                if (context.testExpression("price", targetDepth)) {
                    context.nextToken();
                    product.setPrice(context.getUnmarshaller(Integer.class).unmarshall(context));
                }
                if (context.testExpression("url", targetDepth)) {
                    context.nextToken();
                    product.setUrl(context.getUnmarshaller(String.class).unmarshall(context));
                }
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                }
            }
            token = context.nextToken();
        }

        return product;
    }

    private static ProductJsonUnmarshaller instance;

    public static ProductJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new ProductJsonUnmarshaller();
        return instance;
    }
}
