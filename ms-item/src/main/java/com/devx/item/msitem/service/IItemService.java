package com.devx.item.msitem.service;

import com.devx.item.msitem.model.Item;

import java.util.List;

public interface IItemService {

    List<Item> findAll();

    Item findById(Integer id, Integer count);

}
