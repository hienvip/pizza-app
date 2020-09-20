package com.pycogroup.pitsa.service;


import com.pycogroup.pitsa.api.model.CategoryResponseModel;
import com.pycogroup.pitsa.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseModel findByCategoryName(String categoryName);
    Category addCategory(Category category);
    List<Category> getAllCategories();
}
