package com.pycogroup.pitsa.service;

import com.pycogroup.pitsa.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findByCatId(Integer catId);
    Product addProduct(Product product);
}
