package com.api.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.api.enums.CountryType;
import com.api.service.TaxStrategy;

import jakarta.annotation.PostConstruct;

import static com.api.service.ProductService.addCountryTaxStrategy;

@Service
public class FrenchTaxStrategy implements TaxStrategy {
    private Double tvaTax = .2;

    @PostConstruct
    @Override
    public void register() {
        addCountryTaxStrategy(CountryType.FRANCE, this);
    }

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        BigDecimal tvaTaxCalculated = price.multiply(BigDecimal.valueOf(tvaTax));
        return price.add(tvaTaxCalculated);
    }
}
