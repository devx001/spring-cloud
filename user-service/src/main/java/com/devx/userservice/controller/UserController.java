package com.devx.userservice.controller;

import com.devx.commonuser.model.entity.User;
import com.devx.userservice.service.IUserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  private final IUserService userService;

  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable(name = "id") Integer id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<User> findById(@PathVariable(name = "username") String username) {
    User user = userService.findByUsername(username);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return new ResponseEntity(userService.save(user), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Integer id,
      @RequestBody User user) {

    User userFound = userService.findById(id);

    userFound.setName(user.getName());
    userFound.setEmail(user.getEmail());
    userFound.setAttempts(user.getAttempts());

    return new ResponseEntity(userService.save(userFound), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteUser(@PathVariable Integer id) {
    userService.deleteById(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
