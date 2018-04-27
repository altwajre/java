package com.company.app;

public class App {
  public static void main(String[] args) {
    Offer phoneOffer = new Offer("phone_offer");
    Product iPhone = new Product("iPhone");

    phoneOffer.setProduct(iPhone);

    System.out.println(phoneOffer);
  }
}
/*
Offer(id=0, name=phone_offer, product=Product(id=0, name=iPhone))
 */
