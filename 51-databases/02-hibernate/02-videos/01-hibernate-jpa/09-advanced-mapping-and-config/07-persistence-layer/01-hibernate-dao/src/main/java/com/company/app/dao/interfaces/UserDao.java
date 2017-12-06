package com.company.app.dao.interfaces;

import com.company.app.model.User;

import java.util.List;

public interface UserDao extends Dao<User,Long> {
  List<User> findByFirstName(String firstName);
}
