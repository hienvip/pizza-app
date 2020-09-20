package com.pycogroup.pitsa.model;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@QueryEntity
@Document(collection = "security_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId _id;

    @Getter
    @Setter
    private int roleId;

    @Getter
    @Setter
    private String roleName;

    @Getter
    @Setter
    private String description;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
