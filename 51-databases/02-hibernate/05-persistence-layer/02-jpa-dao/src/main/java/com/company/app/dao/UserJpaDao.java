package com.company.app.dao;

import com.company.app.dao.interfaces.UserDao;
import com.company.app.model.User;

import java.util.List;

public class UserJpaDao extends AbstractDao<User,Long> implements UserDao {
  @Override
  public List<User> findByFirstName(String firstName) {
    return null;
  }
}
