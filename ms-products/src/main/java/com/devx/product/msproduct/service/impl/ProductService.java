package com.devx.product.msproduct.service.impl;

import com.devx.commons.model.entity.Product;
import com.devx.product.msproduct.model.repository.IProductRepository;
import com.devx.product.msproduct.service.IProductService;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(
        IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

}
