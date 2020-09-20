package com.pycogroup.pitsa.service.impl;

import com.pycogroup.pitsa.model.Product;
import com.pycogroup.pitsa.repository.ProductRepository;
import com.pycogroup.pitsa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findByCatId(Integer catId) {
        return productRepository.findByCatId(catId);
    }

    @Override
    public Product addProduct(Product product) {
        if(!productRepository.existsById(product.getId())){
            return productRepository.save(product);
        }
        else
            throw new RuntimeException("Record already exists");
    }

}
