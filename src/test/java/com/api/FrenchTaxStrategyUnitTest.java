package com.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.service.impl.FrenchTaxStrategy;

@ExtendWith(MockitoExtension.class)
public class FrenchTaxStrategyUnitTest {

    @InjectMocks
    private FrenchTaxStrategy frenchTaxStrategy;

    @Test
    public void testCalculateTax() {
        BigDecimal price = BigDecimal.valueOf(100);
        BigDecimal expectedPrice = BigDecimal.valueOf(120);

        BigDecimal priceCalculated = frenchTaxStrategy.calculateTax(price);

        assertTrue(priceCalculated.compareTo(expectedPrice) == 0);
    }

    @Test
    public void testCalculateTaxWithZeroPrice() {
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal expectedPrice = BigDecimal.ZERO;
        BigDecimal priceCalculated = frenchTaxStrategy.calculateTax(price);

        assertTrue(priceCalculated.compareTo(expectedPrice) == 0);
    }
}