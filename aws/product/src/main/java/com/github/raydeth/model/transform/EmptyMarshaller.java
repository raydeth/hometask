/**

*/
package com.github.raydeth.model.transform;



import com.amazonaws.SdkClientException;
import com.github.raydeth.model.*;

import com.amazonaws.protocol.*;
import com.amazonaws.annotation.SdkInternalApi;

/**
 * EmptyMarshaller
 */
@SdkInternalApi
public class EmptyMarshaller {

    private static final EmptyMarshaller instance = new EmptyMarshaller();

    public static EmptyMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(Empty empty, ProtocolMarshaller protocolMarshaller) {

        if (empty == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
