package com.github.raydeth.productapp.controller;

import com.github.raydeth.productapp.aws.AwsProductService;
import com.github.raydeth.productapp.aws.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final AwsProductService service;

    @PostMapping("")
    public void upload(@RequestBody Product product) {
        product.setId(null);
        service.upload(product);
    }

    @PutMapping("")
    public void update(@RequestBody Product product) {
        service.update(product);
    }

}
