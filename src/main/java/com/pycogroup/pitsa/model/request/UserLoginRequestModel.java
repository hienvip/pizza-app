package com.pycogroup.pitsa.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestModel {
    @Getter
    @Setter
    @NotNull
    private String requiredEntry;

    @Getter
    @Setter
    @NotNull
    private String password;



}
