package com.projectmanagement.api.responces;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectIssuesResponce {
    private String uid;
    private String description;
    private Date dateCreation;
}
