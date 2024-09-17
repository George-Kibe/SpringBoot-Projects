package com.kibe.InventoryMs.service;

public interface InventoryService {
    public boolean isInStock(String skuCode, Integer quantity);
}
