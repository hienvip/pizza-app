package com.pycogroup.pitsa.repository;

import com.pycogroup.pitsa.api.model.CategoryResponseModel;
import com.pycogroup.pitsa.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends MongoRepository<Category, String>{
    CategoryResponseModel findByCategoryName(String categoryName);
}
