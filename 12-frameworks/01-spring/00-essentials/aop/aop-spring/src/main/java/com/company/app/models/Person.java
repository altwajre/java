package com.company.app.models;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class Person {
  private String name;
  private int age;
}
