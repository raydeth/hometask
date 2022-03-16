package com.github.raydeth;

import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;
import com.github.raydeth.model.PostUploadProductRequest;
import com.github.raydeth.model.PostUploadProductResult;
import com.github.raydeth.model.PutUploadProductRequest;
import com.github.raydeth.model.PutUploadProductResult;

import java.util.Scanner;

public class Main {

    private Product productSdk;

    public Main() {
        initSdk();
    }

    private void initSdk() {
        productSdk = new ProductClientBuilder()
                .connectionConfiguration(
                        new ConnectionConfiguration()
                                .maxConnections(100)
                                .connectionMaxIdleMillis(1000))
                .timeoutConfiguration(
                        new TimeoutConfiguration()
                                .httpRequestTimeout(30000)
                                .totalExecutionTimeout(100000)
                                .socketTimeout(20000))
                .build();

    }

    public void execute() {
        Scanner in = new Scanner(System.in);
        CommandEnum command = CommandEnum.valueOf(in.nextLine().toUpperCase());
        switch (command) {
            case UPLOAD: {
                System.out.print("Product name: ");
                String productName = in.nextLine();

                System.out.print("Product url: ");
                String productUrl = in.nextLine();

                System.out.print("Product price: ");
                Integer productPrice = in.nextInt();

                createProduct(productName, productUrl, productPrice);
                break;
            }
            case UPDATE: {
                System.out.print("Product id: ");
                String productId = in.nextLine();

                System.out.print("Product name: ");
                String productName = in.nextLine();

                System.out.print("Product url: ");
                String productUrl = in.nextLine();

                System.out.print("Product price: ");
                Integer productPrice = in.nextInt();

                updateProduct(productId, productName, productUrl, productPrice);
                break;
            }
        }

        productSdk.shutdown();
    }

    public void createProduct(String name, String url, Integer price) {
        com.github.raydeth.model.Product product = new com.github.raydeth.model.Product();
        product.setName(name);
        product.setUrl(url);
        product.setPrice(price);
        PostUploadProductResult postUploadProductResult = productSdk.postUploadProduct(new PostUploadProductRequest()
                .product(product));

        if (postUploadProductResult.sdkResponseMetadata().httpStatusCode() == 200) {
            System.out.println("Successfully uploaded");
        }
        return;
    }

    public void updateProduct(String id, String name, String url, Integer price) {
        com.github.raydeth.model.Product product = new com.github.raydeth.model.Product();
        product.setId(id);
        product.setName(name);
        product.setUrl(url);
        product.setPrice(price);
        PutUploadProductResult putUploadProductResult = productSdk.putUploadProduct(new PutUploadProductRequest()
                .product(product));
        if (putUploadProductResult.sdkResponseMetadata().httpStatusCode() == 200) {
            System.out.println("Successfully update");
        }
        return;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.execute();
    }
}
