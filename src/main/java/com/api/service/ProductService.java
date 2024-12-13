package com.api.service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.enums.CountryType;
import com.api.exceptions.ResourceNotFoundException;
import com.api.model.Product;
import com.api.repository.ProductRepository;

import lombok.Data;

@Data
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static HashMap<CountryType, TaxStrategy> taxStrategyMap = new HashMap<>();

    public Product getProduct(final Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found for id : " + id));
        return product;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public static void addCountryTaxStrategy(CountryType countryType, TaxStrategy taxStrategy) {
        taxStrategyMap.put(countryType, taxStrategy);
    }

    public BigDecimal getProductPrice(final Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found for id : " + id));
        return taxStrategyMap.get(product.getCountry()).calculateTax(product.getPrice());
    }

}
