package com.api.service;

import java.math.BigDecimal;

public interface TaxStrategy {

    void register();

    public BigDecimal calculateTax(BigDecimal price);
}
