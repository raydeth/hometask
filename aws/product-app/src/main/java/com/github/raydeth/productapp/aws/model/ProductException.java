/**

*/
package com.github.raydeth.productapp.aws.model;

import com.amazonaws.annotation.SdkInternalApi;
import com.amazonaws.opensdk.SdkErrorHttpMetadata;
import com.amazonaws.opensdk.internal.BaseException;


/**
 * Base exception for all com.github.raydeth.service exceptions thrown by upload-product-API
 */
public class ProductException extends com.amazonaws.SdkBaseException implements BaseException {

    private static final long serialVersionUID = 1L;

    private SdkErrorHttpMetadata sdkHttpMetadata;

    private String message;

    /**
     * Constructs a new ProductException with the specified error message.
     *
     * @param message
     *        Describes the error encountered.
     */
    public ProductException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public ProductException sdkHttpMetadata(SdkErrorHttpMetadata sdkHttpMetadata) {
        this.sdkHttpMetadata = sdkHttpMetadata;
        return this;
    }

    @Override
    public SdkErrorHttpMetadata sdkHttpMetadata() {
        return sdkHttpMetadata;
    }

    @SdkInternalApi
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
