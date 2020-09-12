package com.projectmanagement.api.responces;

import com.projectmanagement.api.entities.Task;
import com.projectmanagement.api.entities.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserResponce {

    private Long id;
    private String uid;
    private String email;
    private String username;
    private Date date_created;
    private Date date_modified;
    private int time_modified_number;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isEnabled;



    private List<Task> tasks;
    private UserProfileResponce userProfile;
}
