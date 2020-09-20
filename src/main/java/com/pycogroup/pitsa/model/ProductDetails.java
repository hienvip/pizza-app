package com.pycogroup.pitsa.model;


import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@QueryEntity
@Document(collection = "product_details")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId _id;

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private int proId;

    @Getter
    @Setter
    private String cheese;

    @Getter
    @Setter
    private String crust;

    @Getter
    @Setter
    private Double price;
}
