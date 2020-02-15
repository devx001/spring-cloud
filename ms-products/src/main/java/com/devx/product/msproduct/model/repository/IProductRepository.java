package com.devx.product.msproduct.model.repository;

import com.devx.commons.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
