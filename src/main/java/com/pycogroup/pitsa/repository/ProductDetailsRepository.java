package com.pycogroup.pitsa.repository;

import com.pycogroup.pitsa.model.ProductDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductDetailsRepository extends MongoRepository<ProductDetails, ObjectId> {
    List<ProductDetails> findByProId(int id);
}
