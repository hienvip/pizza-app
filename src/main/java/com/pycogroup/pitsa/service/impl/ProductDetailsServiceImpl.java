package com.pycogroup.pitsa.service.impl;

import com.pycogroup.pitsa.model.ProductDetails;
import com.pycogroup.pitsa.repository.ProductDetailsRepository;
import com.pycogroup.pitsa.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductDetailsServiceImpl implements ProductDetailsService {
    @Autowired
    private ProductDetailsRepository repository;

    @Override
    public List<ProductDetails> findByProId(int id) {
        return repository.findByProId(id);
    }

    @Override
    public ProductDetails addProductDetails(ProductDetails productDetails) {
        return repository.save(productDetails);
    }

}
