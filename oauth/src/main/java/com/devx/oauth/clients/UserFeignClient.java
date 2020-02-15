package com.devx.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.devx.commonuser.model.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-user")
public interface UserFeignClient {

  @GetMapping("/user/username/{username}")
  User findByUsername(@RequestParam String username);

}
