package com.projectmanagement.api.dto;

import com.projectmanagement.api.entities.common.CommonEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class UserDto {

    //region Fields Entity
    private long id;
    private String uid;
    private String email;
    private String username;
    private String password;
    private Date date_created =new Date();
    private Date date_modified;
    private int time_modified_number;
    private boolean isTokenExpired=false;
    private boolean isAccountNonExpired=true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired=true;
    private boolean isEnabled=true;

    private UserProfileDto userProfile;
    //endregion

}
