/**

*/
package com.github.raydeth.productapp.aws;


import com.github.raydeth.productapp.aws.model.PostUploadProductRequest;
import com.github.raydeth.productapp.aws.model.PostUploadProductResult;
import com.github.raydeth.productapp.aws.model.PutUploadProductRequest;
import com.github.raydeth.productapp.aws.model.PutUploadProductResult;

/**
 * Interface for accessing Product.
 */
public interface Product {

    /**
     * @param postUploadProductRequest
     * @return Result of the PostUploadProduct operation returned by the service.
     * @sample Product.PostUploadProduct
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/uwe8mvlvh3-2022-03-15T16:45:01Z/PostUploadProduct"
     *      target="_top">AWS API Documentation</a>
     */
    PostUploadProductResult postUploadProduct(PostUploadProductRequest postUploadProductRequest);

    /**
     * @param putUploadProductRequest
     * @return Result of the PutUploadProduct operation returned by the service.
     * @sample Product.PutUploadProduct
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/uwe8mvlvh3-2022-03-15T16:45:01Z/PutUploadProduct"
     *      target="_top">AWS API Documentation</a>
     */
    PutUploadProductResult putUploadProduct(PutUploadProductRequest putUploadProductRequest);

    /**
     * @return Create new instance of builder with all defaults set.
     */
    public static ProductClientBuilder builder() {
        return new ProductClientBuilder();
    }

    /**
     * Shuts down this client object, releasing any resources that might be held open. This is an optional method, and
     * callers are not expected to call it, but can if they want to explicitly release any open resources. Once a client
     * has been shutdown, it should not be used to make any more requests.
     */
    void shutdown();

}
