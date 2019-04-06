package com.company.app.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @Autowired
  ApplicationEventPublisher publisher;

  @RequestMapping("/")
  public String home() {
    this.publisher.publishEvent(new CustomSpringEvent(this, "pub.Hello"));
    return "Home";
  }
}
