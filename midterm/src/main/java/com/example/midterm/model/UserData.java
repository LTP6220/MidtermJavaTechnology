package com.example.midterm.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserData implements Serializable {
    private String email;

    private String password;
}
