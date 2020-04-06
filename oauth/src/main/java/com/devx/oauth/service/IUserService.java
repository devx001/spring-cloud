package com.devx.oauth.service;

import com.devx.commonuser.model.entity.User;

public interface IUserService {

  User findByUsername(String username);

  User updateUsername(User user, Integer id);

}
