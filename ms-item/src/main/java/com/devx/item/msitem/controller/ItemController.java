package com.devx.item.msitem.controller;

import com.devx.item.msitem.model.Item;
import com.devx.item.msitem.model.Product;
import com.devx.item.msitem.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@Validated
@Api(tags = "/item")
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @Value("${configuration.text}")
    private String text;


    @ApiOperation(
            value = "Gets ITEMS",
            notes = "On call, to get items",
            response = Product.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    @GetMapping("/")
    public ResponseEntity<List<Item>> findAll (){
        return ResponseEntity.ok(itemService.findAll());
    }

    @ApiOperation(
            value = "Gets item",
            notes = "On call, to get item",
            response = Product.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
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

    @ApiOperation(
            value = "Create item",
            notes = "On call, to create item",
            response = Product.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    @PostMapping("/")
    public ResponseEntity<Item> create (@RequestBody Item item){
        return ResponseEntity.ok(item);
    }

    @ApiOperation(
            value = "Create item",
            notes = "On call, to create item",
            response = Product.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    @PostMapping("/multiple")
    public ResponseEntity<List<Item>> createList (@RequestBody List<Item> item){
        return ResponseEntity.ok(item);
    }

    @GetMapping("/config")
    public ResponseEntity<?> getConfigs (@Value("${server.port}") String port){
        Map<String, String> json = new HashMap<>();
        json.put("port", port);
        json.put("text", text);
        return ResponseEntity.ok(json);

    }

}
