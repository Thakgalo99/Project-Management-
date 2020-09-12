package com.projectmanagement.api.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectIssuesRequest {

    private String description;
    private Date dateCreation;
}
