package com.devx.userservice.model.repository;

import com.devx.commonuser.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);

}
