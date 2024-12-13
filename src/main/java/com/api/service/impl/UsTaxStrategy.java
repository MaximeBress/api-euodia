package com.api.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.api.enums.CountryType;
import com.api.strategies.TaxStrategy;

import jakarta.annotation.PostConstruct;

import static com.api.service.ProductService.addCountryTaxStrategy;

@Service
public class UsTaxStrategy implements TaxStrategy {
    private Double salesTax = .08;

    @PostConstruct
    @Override
    public void register() {
        addCountryTaxStrategy(CountryType.USA, this);
    }

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        BigDecimal salesTaxCalculated = price.multiply(BigDecimal.valueOf(salesTax));
        return price.add(salesTaxCalculated);
    }
}
