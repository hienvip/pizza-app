package com.pycogroup.pitsa.service;

import com.pycogroup.pitsa.model.ProductDetails;

import java.util.List;

public interface ProductDetailsService {
    List<ProductDetails> findByProId(int id);
    ProductDetails addProductDetails(ProductDetails productDetails);
}
