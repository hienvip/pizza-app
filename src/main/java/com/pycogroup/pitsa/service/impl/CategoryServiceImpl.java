package com.pycogroup.pitsa.service.impl;

import com.pycogroup.pitsa.api.model.CategoryResponseModel;
import com.pycogroup.pitsa.model.Category;
import com.pycogroup.pitsa.repository.CategoryRepository;
import com.pycogroup.pitsa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseModel findByCategoryName(String name) {
        name = name.substring(0,1).toUpperCase() + name.substring(1);
       return categoryRepository.findByCategoryName(name);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
