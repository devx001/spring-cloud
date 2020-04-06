package com.devx.oauth.config;

import com.devx.commonuser.model.entity.User;
import com.devx.oauth.service.IUserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class AditionalInfoToken implements TokenEnhancer {

  private final IUserService userService;

  public AditionalInfoToken(IUserService userService) {
    this.userService = userService;
  }

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {

    User user = userService.findByUsername(authentication.getName());

    Map<String, Object> info = new HashMap<>();
    info.put("name", user.getName());
    info.put("lastName", user.getLastName());
    info.put("email", user.getEmail());

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

    return accessToken;
  }
}
