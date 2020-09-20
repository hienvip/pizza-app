package com.pycogroup.pitsa.controller;

import com.pycogroup.pitsa.api.ProductDetailApi;
import com.pycogroup.pitsa.api.model.CreateProductDetailRequest;
import com.pycogroup.pitsa.api.model.ObjectCreationSuccessResponse;
import com.pycogroup.pitsa.api.model.ProductDetailListResponse;
import com.pycogroup.pitsa.api.model.ProductDetailResponseModel;
import com.pycogroup.pitsa.model.ProductDetails;
import com.pycogroup.pitsa.service.ProductDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public class ProductDetailsController implements ProductDetailApi {
    @Autowired
    private ProductDetailsService service;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> createProductDetail(@Valid CreateProductDetailRequest createProductDetailRequest) {
        ProductDetails productDetails = modelMapper.map(createProductDetailRequest, ProductDetails.class);
        ProductDetails persistProductDetails = service.addProductDetails(productDetails);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(Integer.valueOf(persistProductDetails.getId()).toString());
        result.responseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<ObjectCreationSuccessResponse>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDetailListResponse> getProductDetailByProId(Integer proId) {
        List<ProductDetails> productDetails = service.findByProId(proId);
        return buildProductDetailListResponse(productDetails);
    }

    private ResponseEntity<ProductDetailListResponse> buildProductDetailListResponse(List<ProductDetails> productDetailsList){
        ProductDetailListResponse productDetailListResponse = new ProductDetailListResponse();
        if(productDetailsList != null){
            productDetailsList.forEach(item -> productDetailListResponse.addProductDetailItem(modelMapper.map(item,ProductDetailResponseModel.class)));
        }
        return new ResponseEntity<>(productDetailListResponse, HttpStatus.OK);
    }

}
