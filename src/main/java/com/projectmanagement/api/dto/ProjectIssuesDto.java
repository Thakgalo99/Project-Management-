package com.projectmanagement.api.dto;

import com.projectmanagement.api.entities.Project;
import com.projectmanagement.api.entities.Status;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ProjectIssuesDto {

    private long id;
    private String uid;
    private String description;
    private Date dateCreation;
    private Status status;
    private Project project;
}
