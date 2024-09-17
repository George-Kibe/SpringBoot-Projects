package com.kibe.InventoryMs.service;

import com.kibe.InventoryMs.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImplementation implements InventoryService {
    private final InventoryRepository inventoryRepository;
    @Override
    public boolean isInStock(String skuCode, Integer quantity) {
        // find an inventory for a given skuCode where quantity is >=0
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
