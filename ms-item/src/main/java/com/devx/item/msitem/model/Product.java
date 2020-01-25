package com.devx.item.msitem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Product implements Serializable {

    private Integer id;
    private String name;
    private double price;
    private Date createdAt;
    private Integer port;

}
