package com.company.app.stubs;

import com.company.app.Basket;

public class EnterprisyBasket extends Basket {

    public EnterprisyBasket(ServiceLocator serviceLocator)
    {
        setWarehouseInventory(serviceLocator.getWarehouseInventory());
    }
}
