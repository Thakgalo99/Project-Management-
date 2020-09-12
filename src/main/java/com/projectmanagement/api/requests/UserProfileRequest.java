package com.projectmanagement.api.requests;

import com.projectmanagement.api.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserProfileRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private String address;
    private String city;
    private String street;
    private String postal_code;
    private String type;
}
