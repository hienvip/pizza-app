package com.pycogroup.pitsa.controller;

import com.pycogroup.pitsa.api.CategoryApi;
import com.pycogroup.pitsa.api.model.CategoryListResponse;
import com.pycogroup.pitsa.api.model.CategoryResponseModel;
import com.pycogroup.pitsa.api.model.CreateCategoryRequest;
import com.pycogroup.pitsa.api.model.ObjectCreationSuccessResponse;
import com.pycogroup.pitsa.model.Category;
import com.pycogroup.pitsa.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@Slf4j
public class CategoryController implements CategoryApi {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> createCategory(@Valid CreateCategoryRequest createCategoryRequest) {
        Category category = modelMapper.map(createCategoryRequest,Category.class);
        Category persistCategory = categoryService.addCategory(category);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(persistCategory.toString());
        result.setResponseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<ObjectCreationSuccessResponse>(result,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryListResponse> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return buildCategoryListResponse(categoryList);
    }

    @Override
    public ResponseEntity<CategoryResponseModel> getCategoryByName(String categoryName) {
        CategoryResponseModel categoryResponseModel = categoryService.findByCategoryName(categoryName);
        return new ResponseEntity<>(categoryResponseModel,HttpStatus.OK);
    }

    private ResponseEntity<CategoryListResponse> buildCategoryListResponse(List<Category> categoryList){
        CategoryListResponse categoryListResponse = new CategoryListResponse();
        if(categoryList != null){
            categoryList.forEach(item -> categoryListResponse.addCategoriesItem(modelMapper.map(item, CategoryResponseModel.class)));
        }
        return new ResponseEntity<>(categoryListResponse,HttpStatus.OK);
    }

}
