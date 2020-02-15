package com.devx.userservice.service;

import com.devx.commonuser.model.entity.User;
import java.util.List;


public interface IUserService {

  List<User> findAll();

  User findById(Integer id);

  User findByUsername(String username);

  User save(User user);

  void deleteById(Integer id);

}
