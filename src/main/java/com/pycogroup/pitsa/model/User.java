package com.pycogroup.pitsa.model;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@QueryEntity
@Document(collection = "users")
@NoArgsConstructor
public class User {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId _id;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String username;

    public User( String phone, String password, String lastname, String firstname, String email ) {
        this.firstname = firstname;
        this.email = email;
        this.phone = phone;
        this.lastname = lastname;
        this.password = password;
    }

    public User(String password, String lastname, String firstname, String username) {
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
