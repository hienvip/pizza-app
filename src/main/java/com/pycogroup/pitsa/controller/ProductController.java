package com.pycogroup.pitsa.controller;

import com.pycogroup.pitsa.api.ProductApi;
import com.pycogroup.pitsa.api.model.CreateProductRequest;
import com.pycogroup.pitsa.api.model.ObjectCreationSuccessResponse;
import com.pycogroup.pitsa.api.model.ProductListResponse;
import com.pycogroup.pitsa.api.model.ProductResponseModel;
import com.pycogroup.pitsa.model.Product;
import com.pycogroup.pitsa.service.ProductService;
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
@Slf4j
@CrossOrigin
public class ProductController implements ProductApi{

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> createProduct(@Valid CreateProductRequest createProductRequest) {
        Product product =  modelMapper.map(createProductRequest, Product.class);
        Product persistProduct = productService.addProduct(product);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(Integer.valueOf(persistProduct.getId()).toString());
        result.setResponseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<ObjectCreationSuccessResponse>(result,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductListResponse> getProductByCatId(Integer id) {
        List<Product> products = productService.findByCatId(id);
        return buildProductListResponse(products);
    }

    private ResponseEntity<ProductListResponse> buildProductListResponse(List<Product> productList) {
        ProductListResponse productListResponse = new ProductListResponse();
        if(productList != null){
            productList.forEach(item -> productListResponse.addProductsItem(modelMapper.map(item, ProductResponseModel.class)));
        }

        return new ResponseEntity<>(productListResponse, HttpStatus.OK);
    }

}
