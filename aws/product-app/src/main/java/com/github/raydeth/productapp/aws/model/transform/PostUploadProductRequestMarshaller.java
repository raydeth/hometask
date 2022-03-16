/**

*/
package com.github.raydeth.productapp.aws.model.transform;


import com.amazonaws.SdkClientException;
import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.protocol.*;
import com.github.raydeth.productapp.aws.model.PostUploadProductRequest;

/**
 * PostUploadProductRequestMarshaller
 */
@SdkInternalApi
public class PostUploadProductRequestMarshaller {

    private static final MarshallingInfo<StructuredPojo> PRODUCT_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).isExplicitPayloadMember(true).build();

    private static final PostUploadProductRequestMarshaller instance = new PostUploadProductRequestMarshaller();

    public static PostUploadProductRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(PostUploadProductRequest postUploadProductRequest, ProtocolMarshaller protocolMarshaller) {

        if (postUploadProductRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(postUploadProductRequest.getProduct(), PRODUCT_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
