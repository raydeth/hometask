/**
 *
 */
package com.github.raydeth;

import com.github.raydeth.model.PostUploadProductRequest;
import com.github.raydeth.model.PostUploadProductResult;
import com.github.raydeth.model.PutUploadProductRequest;
import com.github.raydeth.model.PutUploadProductResult;

/**
 * Abstract implementation of {@code Product}.
 */
public class AbstractProduct implements Product {

    protected AbstractProduct() {
    }

    @Override
    public PostUploadProductResult postUploadProduct(PostUploadProductRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PutUploadProductResult putUploadProduct(PutUploadProductRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

}
