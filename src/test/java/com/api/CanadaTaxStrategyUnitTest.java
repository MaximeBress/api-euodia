package com.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.service.impl.CanadaTaxStrategy;

@ExtendWith(MockitoExtension.class)
public class CanadaTaxStrategyUnitTest {

    @InjectMocks
    private CanadaTaxStrategy canadaTaxStrategy;

    @Test
    public void testCalculateTax() {
        BigDecimal price = BigDecimal.valueOf(100);
        BigDecimal expectedPrice = BigDecimal.valueOf(110);

        BigDecimal priceCalculated = canadaTaxStrategy.calculateTax(price);

        assertTrue(priceCalculated.compareTo(expectedPrice) == 0);
    }

    @Test
    public void testCalculateTaxWithZeroPrice() {
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal expectedPrice = BigDecimal.ZERO;
        BigDecimal priceCalculated = canadaTaxStrategy.calculateTax(price);

        assertTrue(priceCalculated.compareTo(expectedPrice) == 0);
    }
}