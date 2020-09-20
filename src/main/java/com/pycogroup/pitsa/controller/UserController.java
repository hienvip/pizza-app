package com.pycogroup.pitsa.controller;

import com.pycogroup.pitsa.api.UserApi;
import com.pycogroup.pitsa.api.model.CreateUserRequest;
import com.pycogroup.pitsa.api.model.UserLoginRequestModel;
import com.pycogroup.pitsa.api.model.UserLoginResponseModel;
import com.pycogroup.pitsa.api.model.UserResponseModel;
import com.pycogroup.pitsa.model.User;
import com.pycogroup.pitsa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin
public class UserController implements UserApi {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserResponseModel> addUser(@Valid CreateUserRequest createUserRequest) {
        User user = modelMapper.map(createUserRequest, User.class);
        UserResponseModel result = new UserResponseModel();

        if(userService.findByEmail(user.getEmail()).isPresent()){
            result.setMessage("Email have been signed up");
        }
        if(userService.findByPhone(user.getPhone()).isPresent()){
            result.setMessage("Phone have been signed up");
        }

        Optional<String> persistUserToken = userService.signUp
                (user.getPhone(),user.getPassword(),user.getLastname(),user.getFirstname(),user.getEmail());

        if (!persistUserToken.isPresent()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        else{
            result = modelMapper.map(user,UserResponseModel.class);
            result.setToken(userService.formatToken(persistUserToken));
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<UserLoginResponseModel> signIn(@Valid UserLoginRequestModel userLoginRequestModel) {
        String requiredInput = userLoginRequestModel.getRequiredEntry();
        String userPw = userLoginRequestModel.getPassword();

        Optional<String> token = userService.signIn(requiredInput,userPw);
        UserLoginResponseModel result = new UserLoginResponseModel();

        if(!token.isPresent()){
            result.setMessage("Your email, phone or password is wrong");
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        }

        else{
            if(userService.isNumeric(requiredInput)){
                result = modelMapper.map(userService.findByPhone(requiredInput).get(), UserLoginResponseModel.class);
            }
            else{
                result = modelMapper.map(userService.findByEmail(requiredInput).get(), UserLoginResponseModel.class);
            }
            result.setToken(userService.formatToken(token));
            return new ResponseEntity<>(result,HttpStatus.FOUND);
        }
    }

}
