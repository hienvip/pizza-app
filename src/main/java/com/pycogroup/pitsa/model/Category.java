package com.pycogroup.pitsa.model;


import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;

@QueryEntity
@Document(collection = "categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId _id;

    @Getter
    @Setter
    private int catId;

    @Getter
    @Setter
    @NotBlank
    private String categoryName;

    @Getter
    @Setter
    private String imgUrl;
}
