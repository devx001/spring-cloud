package com.devx.item.msitem.service;

import com.devx.item.msitem.model.Item;

import com.devx.item.msitem.model.Product;
import java.util.List;

public interface IItemService {

  List<Item> findAll();

  Item findById(Integer id, Integer count);

  Product save (Product product);

  Product update (Product product, Integer id);

  void delete (Integer id);



}
