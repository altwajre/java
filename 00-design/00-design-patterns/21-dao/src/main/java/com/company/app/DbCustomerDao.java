package com.company.app;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DbCustomerDao implements CustomerDao {

  private static final Logger LOGGER = Logger.getLogger(DbCustomerDao.class);

  private final DataSource dataSource;

  public DbCustomerDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Stream<Customer> getAll() {

    Connection connection;
    try {
      connection = getConnection();
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM CUSTOMERS"); // NOSONAR
      ResultSet resultSet = statement.executeQuery(); // NOSONAR
      return StreamSupport.stream(new Spliterators.AbstractSpliterator<Customer>(Long.MAX_VALUE,
          Spliterator.ORDERED) {

        @Override
        public boolean tryAdvance(Consumer<? super Customer> action) {
          try {
            if (!resultSet.next()) {
              return false;
            }
            action.accept(createCustomer(resultSet));
            return true;
          } catch (SQLException e) {
            throw new RuntimeException(e); // NOSONAR
          }
        }
      }, false).onClose(() -> mutedClose(connection, statement, resultSet));
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private Connection getConnection() {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void mutedClose(Connection connection, PreparedStatement statement, ResultSet resultSet) {
    try {
      resultSet.close();
      statement.close();
      connection.close();
    } catch (SQLException e) {
      LOGGER.info("Exception thrown " + e.getMessage());
    }
  }

  private Customer createCustomer(ResultSet resultSet) {
    try {
      return new Customer(resultSet.getInt("ID"),
          resultSet.getString("FNAME"),
          resultSet.getString("LNAME"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<Customer> getById(int id) {

    ResultSet resultSet = null;

    try (Connection connection = getConnection();
         PreparedStatement statement =
             connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE ID = ?")) {

      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return Optional.of(createCustomer(resultSet));
      } else {
        return Optional.empty();
      }
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    } finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  @Override
  public boolean add(Customer customer) {
    if (getById(customer.getId()).isPresent()) {
      return false;
    }

    try (Connection connection = getConnection();
         PreparedStatement statement =
             connection.prepareStatement("INSERT INTO CUSTOMERS VALUES (?,?,?)")) {
      statement.setInt(1, customer.getId());
      statement.setString(2, customer.getFirstName());
      statement.setString(3, customer.getLastName());
      statement.execute();
      return true;
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }

  @Override
  public boolean update(Customer customer) {
    try (Connection connection = getConnection();
         PreparedStatement statement =
             connection.prepareStatement("UPDATE CUSTOMERS SET FNAME = ?, LNAME = ? WHERE ID = ?")) {
      statement.setString(1, customer.getFirstName());
      statement.setString(2, customer.getLastName());
      statement.setInt(3, customer.getId());
      return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }

  @Override
  public boolean delete(Customer customer) {
    try (Connection connection = getConnection();
         PreparedStatement statement =
             connection.prepareStatement("DELETE FROM CUSTOMERS WHERE ID = ?")) {
      statement.setInt(1, customer.getId());
      return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }
}
