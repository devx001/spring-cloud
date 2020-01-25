package com.devx.item.msitem.restclient;

import com.devx.item.msitem.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-product")
public interface ClientProductRest {

    @GetMapping(value = "/product/")
    List<Product> findAll ();

    @GetMapping(value = "/product/{id}")
    Product findById (@PathVariable(name = "id") Integer id);

}
