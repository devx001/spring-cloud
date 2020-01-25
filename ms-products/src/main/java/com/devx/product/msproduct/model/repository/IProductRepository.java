package com.devx.product.msproduct.model.repository;

import com.devx.product.msproduct.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
