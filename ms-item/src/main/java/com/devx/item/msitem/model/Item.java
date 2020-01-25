package com.devx.item.msitem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    private Product product;
    private Integer cantidad;

    public double getTotal(){
        return product.getPrice() * cantidad;
    }
}
