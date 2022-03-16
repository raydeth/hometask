/**

*/
package com.github.raydeth.model.transform;

import java.math.*;



import com.github.raydeth.model.*;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.*;
import com.amazonaws.transform.*;

import com.fasterxml.jackson.core.JsonToken;
import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * PutUploadProductResult JSON Unmarshaller
 */
public class PutUploadProductResultJsonUnmarshaller implements Unmarshaller<PutUploadProductResult, JsonUnmarshallerContext> {

    public PutUploadProductResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        PutUploadProductResult putUploadProductResult = new PutUploadProductResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return putUploadProductResult;
        }

        while (true) {
            if (token == null)
                break;

            putUploadProductResult.setEmpty(EmptyJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return putUploadProductResult;
    }

    private static PutUploadProductResultJsonUnmarshaller instance;

    public static PutUploadProductResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new PutUploadProductResultJsonUnmarshaller();
        return instance;
    }
}
