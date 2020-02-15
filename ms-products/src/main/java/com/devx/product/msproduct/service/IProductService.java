package com.devx.product.msproduct.service;

import com.devx.product.msproduct.model.entity.Product;

import java.util.List;


public interface IProductService {

  List<Product> findAll();

  Product findById(Integer id);

  Product save(Product product);

  void deleteById(Integer id);

}
