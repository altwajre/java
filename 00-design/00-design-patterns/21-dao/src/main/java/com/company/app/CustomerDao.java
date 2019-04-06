package com.company.app;

import java.util.Optional;
import java.util.stream.Stream;

public interface CustomerDao {

  Stream<Customer> getAll();

  Optional<Customer> getById(int id);

  boolean add(Customer customer);

  boolean update(Customer customer);

  boolean delete(Customer customer);
}
