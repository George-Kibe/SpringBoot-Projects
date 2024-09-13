package com.kibe.ProductMs.service;

import com.kibe.ProductMs.dto.ProductRequest;
import com.kibe.ProductMs.dto.ProductResponse;
import com.kibe.ProductMs.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    Product getProductById(int id);
    ProductResponse  createProduct(ProductRequest productRequest);
}
