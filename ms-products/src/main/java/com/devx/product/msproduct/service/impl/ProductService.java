package com.devx.product.msproduct.service.impl;

import com.devx.product.msproduct.model.entity.Product;
import com.devx.product.msproduct.model.repository.IProductRepository;
import com.devx.product.msproduct.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

}
