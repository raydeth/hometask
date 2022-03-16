/**

*/
package com.github.raydeth;

import java.net.*;
import java.util.*;



import org.apache.commons.logging.*;

import com.amazonaws.*;
import com.amazonaws.opensdk.*;
import com.amazonaws.opensdk.model.*;
import com.amazonaws.opensdk.protect.model.transform.*;
import com.amazonaws.auth.*;
import com.amazonaws.handlers.*;
import com.amazonaws.http.*;
import com.amazonaws.internal.*;
import com.amazonaws.metrics.*;
import com.amazonaws.regions.*;
import com.amazonaws.transform.*;
import com.amazonaws.util.*;
import com.amazonaws.protocol.json.*;

import com.amazonaws.annotation.ThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;

import com.amazonaws.client.ClientHandler;
import com.amazonaws.client.ClientHandlerParams;
import com.amazonaws.client.ClientExecutionParams;
import com.amazonaws.opensdk.protect.client.SdkClientHandler;
import com.amazonaws.SdkBaseException;

import com.github.raydeth.model.*;
import com.github.raydeth.model.transform.*;

/**
 * Client for accessing Product. All service calls made using this client are blocking, and will not return until the
 * service call completes.
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
     * Constructs a new client to invoke service methods on Product using the specified parameters.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not return until the service call
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
     * @return Result of the PostUploadProduct operation returned by the service.
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
     * @return Result of the PutUploadProduct operation returned by the service.
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
