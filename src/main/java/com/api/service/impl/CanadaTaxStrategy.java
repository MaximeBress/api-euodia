package com.api.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.api.enums.CountryType;
import com.api.service.TaxStrategy;

import jakarta.annotation.PostConstruct;

import static com.api.service.ProductService.addCountryTaxStrategy;

@Service
public class CanadaTaxStrategy implements TaxStrategy {
    private Double gstTax = .05;
    private Double pstTax = .05;

    @PostConstruct
    @Override
    public void register() {
        addCountryTaxStrategy(CountryType.CANADA, this);
    }

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        BigDecimal gstTaxCalculated = price.multiply(BigDecimal.valueOf(gstTax));
        BigDecimal pstTaxCalculated = price.multiply(BigDecimal.valueOf(pstTax));

        return price.add(gstTaxCalculated).add(pstTaxCalculated);
    }
}