package com.api.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.enums.CountryType;
import com.api.model.Product;
import com.api.repository.ProductRepository;
import com.api.strategies.TaxStrategy;

import lombok.Data;

@Data
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static HashMap<CountryType, TaxStrategy> taxStrategyMap = new HashMap<>();

    public Optional<Product> getProduct(final Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public static void addCountryTaxStrategy(CountryType countryType, TaxStrategy taxStrategy) {
        taxStrategyMap.put(countryType, taxStrategy);
    }

    public BigDecimal getProductPrice(final Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return taxStrategyMap.get(product.get().getCountry()).calculateTax(product.get().getPrice());
        } else {
            return null;
        }
    }

}
