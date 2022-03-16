/**

*/
package com.github.raydeth.model;

import java.io.Serializable;


/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/uwe8mvlvh3-2022-03-15T16:57:15Z/PostUploadProduct"
 *      target="_top">AWS API Documentation</a>
 */
public class PostUploadProductRequest extends com.amazonaws.opensdk.BaseRequest implements Serializable, Cloneable {

    private Product product;

    /**
     * @param product
     */

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return
     */

    public Product getProduct() {
        return this.product;
    }

    /**
     * @param product
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public PostUploadProductRequest product(Product product) {
        setProduct(product);
        return this;
    }

    /**
     * Returns a string representation of this object. This is useful for testing and debugging. Sensitive data will be
     * redacted from this string using a placeholder value.
     *
     * @return A string representation of this object.
     *
     * @see Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getProduct() != null)
            sb.append("Product: ").append(getProduct());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof PostUploadProductRequest == false)
            return false;
        PostUploadProductRequest other = (PostUploadProductRequest) obj;
        if (other.getProduct() == null ^ this.getProduct() == null)
            return false;
        if (other.getProduct() != null && other.getProduct().equals(this.getProduct()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getProduct() == null) ? 0 : getProduct().hashCode());
        return hashCode;
    }

    @Override
    public PostUploadProductRequest clone() {
        return (PostUploadProductRequest) super.clone();
    }

    /**
     * Set the configuration for this request.
     *
     * @param sdkRequestConfig
     *        Request configuration.
     * @return This object for method chaining.
     */
    public PostUploadProductRequest sdkRequestConfig(com.amazonaws.opensdk.SdkRequestConfig sdkRequestConfig) {
        super.sdkRequestConfig(sdkRequestConfig);
        return this;
    }

}
