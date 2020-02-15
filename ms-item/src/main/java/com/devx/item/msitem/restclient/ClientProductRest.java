package com.devx.item.msitem.restclient;

import com.devx.commons.model.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-product")
public interface ClientProductRest {

    @GetMapping(value = "/product/")
    List<Product> findAll ();

    @GetMapping(value = "/product/{id}")
    Product findById (@PathVariable(name = "id") Integer id);

    @PostMapping(value = "/product/")
    Product save (@RequestBody Product product);

    @PutMapping(value = "/product/{id}")
    Product update (@PathVariable(name = "id") Integer id, @RequestBody Product product);

    @DeleteMapping(value = "/product/{id}")
    void delete (@PathVariable(name = "id") Integer id);

}
