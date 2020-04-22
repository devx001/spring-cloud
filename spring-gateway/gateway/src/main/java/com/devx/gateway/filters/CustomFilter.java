package com.devx.gateway.filters;

import com.devx.gateway.utils.RSAEncryptionJava;
import com.devx.gateway.utils.RSAUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.PrivateKey;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

  @Value("${security.rsa.public-key}")
  private String publicKey;
  @Value("${security.rsa.private-key}")
  private String privateKey;

  private final AbstractGatewayFilterFactory<ModifyRequestBodyGatewayFilterFactory.Config>
      modifyRequestBodyGatewayFilterFactory;

  public CustomFilter(
      AbstractGatewayFilterFactory<ModifyRequestBodyGatewayFilterFactory.Config> modifyRequestBodyGatewayFilterFactory) {
    super(Config.class);
    this.modifyRequestBodyGatewayFilterFactory = modifyRequestBodyGatewayFilterFactory;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return modifyRequestBodyGatewayFilterFactory.apply(
        c -> {
          c.setRewriteFunction(
              CypheringServicesBody.class,
              Map.class,
              (exchange, messageBody) -> {
                try {
                  return Mono.just(getPayload(messageBody.getPayload()));
                } catch (Exception e) {
                  e.printStackTrace();
                }
                return null;
              })
              .setContentType(MediaType.APPLICATION_JSON_VALUE);
        });
  }

  /*@Override
  public GatewayFilter apply(Config config) {
    //Custom Pre Filter. Suppose we can extract JWT and perform Authentication
    return (exchange, chain) -> {
      System.out.println("Public Key: " + publicKey);
      System.out.println("Private Key: " + privateKey);

      System.out.println("Payload Encrypted: " + exchange.getRequest());

      try {
        ServerRequest serverRequest = new DefaultServerRequest(exchange);
        serverRequest.bodyToMono(String.class).subscribe(s -> {
          System.out.println("Request :  " + s);
          try {
            System.out.println("Payload Decrypted: " + getPayload(s));
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
      } catch (Exception e) {
        e.printStackTrace();
      }

      System.out.println("First pre filter" + exchange.getRequest());
      //Custom Post Filter.Suppose we can call error response handler based on error code.
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        System.out.println("First post filter");
      }));
    };
  }*/

  public static class Config {
    // Put the configuration properties
  }

  public Map<String, Object> getPayload(String textEncrypted) throws Exception {
    PrivateKey publicKey = RSAUtils.getPrivateKey(privateKey);
    System.out.println(RSAEncryptionJava.decryptBack(textEncrypted, publicKey));

    String payload = RSAEncryptionJava.decryptBack(textEncrypted, publicKey);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> map = mapper.readValue(payload, Map.class);

    return map;
  }

}