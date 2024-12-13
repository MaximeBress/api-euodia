package com.api.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Product;
import com.api.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Read - Get one product
     * 
     * @param id The id of the product
     * @return A Product object full filled
     */
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") final Long id) {
        Optional<Product> product = productService.getProduct(id);

        if (product.isPresent()) {
            return product.get();
        }

        return null;
    }

    /**
     * Read - Get one product
     * 
     * @param id The id of the product
     * @return A product price based on his country
     */
    @GetMapping("/product/{id}/price")
    public BigDecimal getProductPrice(@PathVariable("id") final Long id) {
        return productService.getProductPrice(id);
    }

    /**
     * Create - Add a new product
     * 
     * @param product An object product
     * @return The Product object saved
     */
    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

}
