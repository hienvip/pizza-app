package com.pycogroup.pitsa.service;

import com.pycogroup.pitsa.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findByCatId(Integer catId);
    Product addProduct(Product product);
    void deleteProduct(Integer proId);
    Product updateProduct(Integer proId);
}
