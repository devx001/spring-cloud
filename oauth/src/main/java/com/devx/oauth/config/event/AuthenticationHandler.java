package com.devx.oauth.config.event;

import com.devx.commonuser.model.entity.User;
import com.devx.oauth.service.IUserService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationHandler implements AuthenticationEventPublisher {

  private final IUserService userService;

  public AuthenticationHandler(IUserService userService) {
    this.userService = userService;
  }

  @Override
  public void publishAuthenticationSuccess(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    log.info("Success Login: " + userDetails.getUsername());
    User user = userService.findByUsername(authentication.getName());
    if(user.getAttempts()>0){
      user.setAttempts(0);
    }
    userService.updateUsername(user, user.getId());
  }

  @Override
  public void publishAuthenticationFailure(AuthenticationException exception,
      Authentication authentication) {
    try{
      User user = userService.findByUsername(authentication.getName());
      log.warn(String.format("Attempt Number: %s ", user.getAttempts()));
      user.setAttempts(user.getAttempts()+1);
      if(user.getAttempts()>=3){
        log.warn(String.format("User: %s was enabled by max attempts", authentication.getName()));
        user.setEnabled(false);
      }
      userService.updateUsername(user, user.getId());
    }catch (FeignException fe){
      log.error(String.format("User: %s does not exit", authentication.getName()));
    }
    log.warn("Error Login: " + exception.getMessage());
  }
}
