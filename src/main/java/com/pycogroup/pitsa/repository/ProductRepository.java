package com.pycogroup.pitsa.repository;

import com.pycogroup.pitsa.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    List<Product> findByCatId(Integer catId);
    boolean existsById(int id);
}
