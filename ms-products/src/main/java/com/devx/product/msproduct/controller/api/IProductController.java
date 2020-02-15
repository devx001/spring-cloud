package com.devx.product.msproduct.controller.api;

import com.devx.commons.model.entity.Product;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Api(tags = "/product")
public interface IProductController {

    @ApiOperation(
            value = "Gets products",
            notes = "On call, to get products",
            response = Product.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    ResponseEntity<List<Product>> findAll();

    @ApiOperation(
            value = "Get product by id",
            notes = "On call, to get product",
            response = Product.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    ResponseEntity<Product> findById(
            @ApiParam(name = "id", value = "id product")
                    Integer id
    ) throws InterruptedException;

    @ApiOperation(
            value = "Get product by id",
            notes = "On call, to get product",
            response = Product.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    ResponseEntity<Product> createProduct(
            @ApiParam(name = "product", value = "object product")
                    Product product
    );

}
