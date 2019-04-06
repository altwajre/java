package com.company.app.pubsub;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
  @Override
  public void onApplicationEvent(CustomSpringEvent event) {
    System.out.println("Start");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Received spring custom event - " + event.getMessage());
  }
}
