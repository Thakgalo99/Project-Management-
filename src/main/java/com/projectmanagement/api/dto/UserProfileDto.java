package com.projectmanagement.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class UserProfileDto {

    private long id;
    private String uid;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private String address;
    private String city;
    private String street;
    private String postal_code;
    private String type;
    private UserDto user;
}
