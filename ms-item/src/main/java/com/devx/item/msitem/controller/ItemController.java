package com.devx.item.msitem.controller;

import com.devx.item.msitem.model.Item;
import com.devx.item.msitem.model.Product;
import com.devx.item.msitem.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService itemService;


    @GetMapping("/")
    public ResponseEntity<List<Item>> findAll (){
        return ResponseEntity.ok(itemService.findAll());
    }

    @HystrixCommand(fallbackMethod = "supportMethod")
    @GetMapping("/{id}/count/{count}")
    public ResponseEntity<Item> findById (@PathVariable(name = "id") Integer id, @PathVariable(name = "count") Integer count){
        return ResponseEntity.ok(itemService.findById(id, 1));
    }

    public ResponseEntity<Item> supportMethod (Integer id, Integer count){
        Item item = new Item();
        Product product = new Product();

        item.setCantidad(count);
        product.setId(id);
        product.setName("Optional Product");
        product.setPrice(123);
        item.setProduct(product);

        return ResponseEntity.ok(item);
    }

}
