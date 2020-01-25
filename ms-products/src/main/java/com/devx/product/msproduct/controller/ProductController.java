package com.devx.product.msproduct.controller;

import com.devx.product.msproduct.controller.api.IProductController;
import com.devx.product.msproduct.model.entity.Product;
import com.devx.product.msproduct.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController implements IProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAll (){
        return ResponseEntity.ok(productService.findAll().stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById (@PathVariable(name = "id") Integer id) {
        Product product = productService.findById(id);
        product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        //Thread.sleep(2000L);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity(product, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
