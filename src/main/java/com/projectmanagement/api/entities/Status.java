package com.projectmanagement.api.entities;

import com.fasterxml.jackson.annotation.*;
import com.projectmanagement.api.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statuses", schema = "public")
public class Status extends CommonEntity {

    //region Fields Entity

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
//endregion

    //region Relationship Entities
//    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
//    private List<Project> projectList;
//
//    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
//    private List<ProjectIssue> projectIssues;
    //endregion
}
