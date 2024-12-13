package com.api.strategies;

import java.math.BigDecimal;

public interface TaxStrategy {

    void register();

    public BigDecimal calculateTax(BigDecimal price);
}
