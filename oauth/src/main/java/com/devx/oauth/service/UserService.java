package com.devx.oauth.service;

import com.devx.commonuser.model.entity.User;
import com.devx.oauth.clients.UserFeignClient;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

  private final UserFeignClient userFeignClient;

  public UserService(UserFeignClient userFeignClient) {
    this.userFeignClient = userFeignClient;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userFeignClient.findByUsername(username);

    if(Objects.isNull(user)){
      log.error("User "+username+" does not exist!");
      throw new UsernameNotFoundException("User "+username+" does not exist!");
    }

    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .peek(simpleGrantedAuthority -> log.info("Role "+simpleGrantedAuthority.getAuthority()))
        .collect(Collectors.toList());

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        authorities
    );
  }
}