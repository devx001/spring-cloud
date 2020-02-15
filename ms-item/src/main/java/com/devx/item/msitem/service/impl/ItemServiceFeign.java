package com.devx.item.msitem.service.impl;

import com.devx.item.msitem.model.Item;
import com.devx.item.msitem.model.Product;
import com.devx.item.msitem.restclient.ClientProductRest;
import com.devx.item.msitem.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceFeign implements IItemService {

  private final ClientProductRest clientProductRest;

  public ItemServiceFeign(
      ClientProductRest clientProductRest) {
    this.clientProductRest = clientProductRest;
  }

  @Override
  public List<Item> findAll() {
    return clientProductRest.findAll().stream().map(product -> new Item(product, 1))
        .collect(Collectors.toList());
  }

  @Override
  public Item findById(Integer id, Integer count) {
    return new Item(clientProductRest.findById(id), count);
  }

  @Override
  public Product save(Product product) {
    return clientProductRest.save(product);
  }

  @Override
  public Product update(Product product, Integer id) {
    return clientProductRest.update(id, product);
  }

  @Override
  public void delete(Integer id) {
    clientProductRest.delete(id);
  }
}
