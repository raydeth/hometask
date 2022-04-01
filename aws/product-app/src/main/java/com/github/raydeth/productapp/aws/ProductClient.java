/**

*/
package com.github.raydeth.productapp.aws;

import com.amazonaws.SdkBaseException;
import com.amazonaws.annotation.ThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;
import com.amazonaws.client.ClientExecutionParams;
import com.amazonaws.client.ClientHandler;
import com.amazonaws.client.ClientHandlerParams;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.opensdk.protect.client.SdkClientHandler;
import com.amazonaws.protocol.json.JsonClientMetadata;
import com.amazonaws.protocol.json.JsonErrorResponseMetadata;
import com.amazonaws.protocol.json.JsonErrorShapeMetadata;
import com.amazonaws.protocol.json.JsonOperationMetadata;
import com.github.raydeth.productapp.aws.model.*;
import com.github.raydeth.productapp.aws.model.transform.PostUploadProductRequestProtocolMarshaller;
import com.github.raydeth.productapp.aws.model.transform.PostUploadProductResultJsonUnmarshaller;
import com.github.raydeth.productapp.aws.model.transform.PutUploadProductRequestProtocolMarshaller;
import com.github.raydeth.productapp.aws.model.transform.PutUploadProductResultJsonUnmarshaller;

import java.util.Arrays;

/**
 * Client for accessing Product. All com.github.raydeth.service calls made using this client are blocking, and will not return until the
 * com.github.raydeth.service call completes.
 * <p>
 * 
 */
@ThreadSafe
class ProductClient implements Product {

    private final ClientHandler clientHandler;

    private static final com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl protocolFactory = new com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl(
            new JsonClientMetadata().withProtocolVersion("1.1").withSupportsCbor(false).withSupportsIon(false).withContentTypeOverride("application/json")
                    .withBaseServiceExceptionClass(ProductException.class));

    /**
     * Constructs a new client to invoke com.github.raydeth.service methods on Product using the specified parameters.
     *
     * <p>
     * All com.github.raydeth.service calls made using this new client object are blocking, and will not return until the com.github.raydeth.service call
     * completes.
     *
     * @param clientParams
     *        Object providing client parameters.
     */
    ProductClient(AwsSyncClientParams clientParams) {
        this.clientHandler = new SdkClientHandler(new ClientHandlerParams().withClientParams(clientParams));
    }

    /**
     * @param postUploadProductRequest
     * @return Result of the PostUploadProduct operation returned by the com.github.raydeth.service.
     * @sample Product.PostUploadProduct
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/uwe8mvlvh3-2022-03-15T16:45:01Z/PostUploadProduct"
     *      target="_top">AWS API Documentation</a>
     */
    @Override
    public PostUploadProductResult postUploadProduct(PostUploadProductRequest postUploadProductRequest) {
        HttpResponseHandler<PostUploadProductResult> responseHandler = protocolFactory.createResponseHandler(new JsonOperationMetadata().withPayloadJson(true)
                .withHasStreamingSuccessResponse(false), new PostUploadProductResultJsonUnmarshaller());

        HttpResponseHandler<SdkBaseException> errorResponseHandler = createErrorResponseHandler();

        return clientHandler.execute(new ClientExecutionParams<PostUploadProductRequest, PostUploadProductResult>()
                .withMarshaller(new PostUploadProductRequestProtocolMarshaller(protocolFactory)).withResponseHandler(responseHandler)
                .withErrorResponseHandler(errorResponseHandler).withInput(postUploadProductRequest));
    }

    /**
     * @param putUploadProductRequest
     * @return Result of the PutUploadProduct operation returned by the com.github.raydeth.service.
     * @sample Product.PutUploadProduct
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/uwe8mvlvh3-2022-03-15T16:45:01Z/PutUploadProduct"
     *      target="_top">AWS API Documentation</a>
     */
    @Override
    public PutUploadProductResult putUploadProduct(PutUploadProductRequest putUploadProductRequest) {
        HttpResponseHandler<PutUploadProductResult> responseHandler = protocolFactory.createResponseHandler(new JsonOperationMetadata().withPayloadJson(true)
                .withHasStreamingSuccessResponse(false), new PutUploadProductResultJsonUnmarshaller());

        HttpResponseHandler<SdkBaseException> errorResponseHandler = createErrorResponseHandler();

        return clientHandler.execute(new ClientExecutionParams<PutUploadProductRequest, PutUploadProductResult>()
                .withMarshaller(new PutUploadProductRequestProtocolMarshaller(protocolFactory)).withResponseHandler(responseHandler)
                .withErrorResponseHandler(errorResponseHandler).withInput(putUploadProductRequest));
    }

    /**
     * Create the error response handler for the operation.
     * 
     * @param errorShapeMetadata
     *        Error metadata for the given operation
     * @return Configured error response handler to pass to HTTP layer
     */
    private HttpResponseHandler<SdkBaseException> createErrorResponseHandler(JsonErrorShapeMetadata... errorShapeMetadata) {
        return protocolFactory.createErrorResponseHandler(new JsonErrorResponseMetadata().withErrorShapes(Arrays.asList(errorShapeMetadata)));
    }

    @Override
    public void shutdown() {
        clientHandler.shutdown();
    }

}
