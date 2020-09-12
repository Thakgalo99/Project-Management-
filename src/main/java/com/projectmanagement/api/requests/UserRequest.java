package com.projectmanagement.api.requests;

import com.projectmanagement.api.entities.Task;
import com.projectmanagement.api.entities.UserProfile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class UserRequest {

    @NotNull(message="This field must not be null !")
    @Size(min = 3,message="This field must have at least 3 characters !")
    private String username;

    @NotNull(message="This field must not be null !")
    @Email()
    private String email;
    
    @NotNull(message="This field must not be null !")
    @Size(min = 8,message="This field must have at least 8 characters !")
    @Size(max=12, message="Password must have a maximum of 12 characters !")
    @Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message="This password must have letters in uppercase and lowercase and number")
    private String password;


//    private List<Task> tasks;
//
   private UserProfileRequest userProfile;
}
