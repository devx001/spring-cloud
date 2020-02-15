package com.devx.item.msitem.service.impl;

import com.devx.item.msitem.model.Item;
import com.devx.item.msitem.model.Product;
import com.devx.item.msitem.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemService implements IItemService {

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<Item> findAll() {
    Product[] products = restTemplate.getForObject("http://ms-product/product/", Product[].class);
    List<Product> productList = Arrays.asList(products);
    return productList.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
  }

  @Override
  public Item findById(Integer id, Integer count) {
    Map<String, String> params = new HashMap<>();
    params.put("id", id.toString());
    Product product = restTemplate
        .getForObject("http://ms-product/product/{id}", Product.class, params);
    return new Item(product, count);
  }

  @Override
  public Product save(Product product) {
    HttpEntity<Product> body = new HttpEntity<>(product);
    ResponseEntity<Product> response = restTemplate
        .exchange("http://ms-product/product/", HttpMethod.POST, body, Product.class);
    return response.getBody();
  }

  @Override
  public Product update(Product product, Integer id) {
    Map<String, String> params = new HashMap<>();
    params.put("id", id.toString());

    HttpEntity<Product> body = new HttpEntity<>(product);
    ResponseEntity<Product> response = restTemplate
        .exchange("http://ms-product/product/{id}", HttpMethod.PUT, body, Product.class, params);
    return response.getBody();
  }

  @Override
  public void delete(Integer id) {
    Map<String, String> params = new HashMap<>();
    params.put("id", id.toString());

    restTemplate.delete("http://ms-product/product/{id}", params);
  }
}
