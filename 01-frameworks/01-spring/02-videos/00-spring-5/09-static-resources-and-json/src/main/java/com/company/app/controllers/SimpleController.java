package com.company.app.controllers;

import com.company.app.dto.Customer;
import com.company.app.dto.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SimpleController {

//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }

    @RequestMapping("/myError")
    public void getError() throws Exception {
        System.out.println("myError");
        throw new Exception("Error handling example");
    }

    @RequestMapping("/orders")
    public Order getOrders() {
        return getOrder();
    }

    private Order getOrder() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setId(3);

        Order order = new Order();
        order.setId(1);
        order.setCustomer(customer);
        order.setOrderDate(new Date());
        order.setProductName("Acme Portal");
        order.setQuantity(1);

        return order;
    }
}
