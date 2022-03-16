/**

*/
package com.github.raydeth.model.transform;



import com.amazonaws.SdkClientException;
import com.github.raydeth.model.*;

import com.amazonaws.protocol.*;
import com.amazonaws.annotation.SdkInternalApi;

/**
 * PutUploadProductRequestMarshaller
 */
@SdkInternalApi
public class PutUploadProductRequestMarshaller {

    private static final MarshallingInfo<StructuredPojo> PRODUCT_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).isExplicitPayloadMember(true).build();

    private static final PutUploadProductRequestMarshaller instance = new PutUploadProductRequestMarshaller();

    public static PutUploadProductRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(PutUploadProductRequest putUploadProductRequest, ProtocolMarshaller protocolMarshaller) {

        if (putUploadProductRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(putUploadProductRequest.getProduct(), PRODUCT_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
