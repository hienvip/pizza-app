package com.pycogroup.pitsa.model;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@QueryEntity
@Document(collection = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId _id;

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private int catId;

    @Getter
    @Setter
    @NotBlank
    private String name;

    @Getter
    @Setter
    private SizePrice price;

    @Getter
    @Setter
    private String imgUrl;

    @Getter
    @Setter
    private String description;

    public Product(int id, int catId, @NotBlank String name, SizePrice price, String imgUrl, String description) {
        this.id = id;
        this.catId = catId;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(_id, product._id) &&
                Objects.equals(id, product.id) &&
                Objects.equals(catId, product.catId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(imgUrl, product.imgUrl) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, id, catId, name, price, imgUrl, description);
    }
}
