/**

*/
package com.github.raydeth.productapp.aws.model.transform;


import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.fasterxml.jackson.core.JsonToken;
import com.github.raydeth.productapp.aws.model.PostUploadProductResult;

import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

/**
 * PostUploadProductResult JSON Unmarshaller
 */
public class PostUploadProductResultJsonUnmarshaller implements Unmarshaller<PostUploadProductResult, JsonUnmarshallerContext> {

    public PostUploadProductResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        PostUploadProductResult postUploadProductResult = new PostUploadProductResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return postUploadProductResult;
        }

        while (true) {
            if (token == null)
                break;

            postUploadProductResult.setEmpty(EmptyJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return postUploadProductResult;
    }

    private static PostUploadProductResultJsonUnmarshaller instance;

    public static PostUploadProductResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new PostUploadProductResultJsonUnmarshaller();
        return instance;
    }
}
