package com.company.app.stubs;

import com.company.app.Product;

public interface ShippingCalculator {
    int findShippingCostFor(Product product, int times);
}
