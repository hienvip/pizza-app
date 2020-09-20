package com.pycogroup.pitsa.service;

import com.pycogroup.pitsa.model.User;

import java.util.Optional;

public interface UserService {

    Optional<String> signIn(String requiredEntry, String password);
    Optional<String> signUp(String phone, String password, String lastname, String firstame,String email);
    Optional<User> signUpToUser(String email, String phone, String firstName, String lastName,String password);
    Optional<User> signInToUser(String requiredEntry, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    String formatToken(Optional<String> token);
    boolean isNumeric (String requiredInput);

}
