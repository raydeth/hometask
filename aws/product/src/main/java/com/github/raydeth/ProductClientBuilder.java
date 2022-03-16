/**

*/
package com.github.raydeth;

import com.amazonaws.annotation.NotThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;
import com.amazonaws.opensdk.protect.client.SdkSyncClientBuilder;
import com.amazonaws.opensdk.internal.config.ApiGatewayClientConfigurationFactory;
import com.amazonaws.util.RuntimeHttpUtils;
import com.amazonaws.Protocol;

import java.net.URI;


/**
 * Fluent builder for {@link Product}.
 * 
 * @see Product#builder
 **/
@NotThreadSafe
public final class ProductClientBuilder extends SdkSyncClientBuilder<ProductClientBuilder, Product> {

    private static final URI DEFAULT_ENDPOINT = RuntimeHttpUtils.toUri("uwe8mvlvh3.execute-api.us-east-1.amazonaws.com", Protocol.HTTPS);
    private static final String DEFAULT_REGION = "us-east-1";

    /**
     * Package private constructor - builder should be created via {@link Product#builder()}
     */
    ProductClientBuilder() {
        super(new ApiGatewayClientConfigurationFactory());
    }

    /**
     * Construct a synchronous implementation of Product using the current builder configuration.
     *
     * @param params
     *        Current builder configuration represented as a parameter object.
     * @return Fully configured implementation of Product.
     */
    @Override
    protected Product build(AwsSyncClientParams params) {
        return new ProductClient(params);
    }

    @Override
    protected URI defaultEndpoint() {
        return DEFAULT_ENDPOINT;
    }

    @Override
    protected String defaultRegion() {
        return DEFAULT_REGION;
    }

}
