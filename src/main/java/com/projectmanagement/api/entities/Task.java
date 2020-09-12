package com.projectmanagement.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectmanagement.api.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks",schema = "public")
public class Task extends CommonEntity {

    //region Fields Entity

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation=new Date();

    @Column(name = "timeCreation")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="HH:mm:ss")
    private Date timeCreation =new Date();

    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "owner",nullable = false)
    private String owner;

    @Column(name = "isActivePasive")
    private Boolean isActivePasive;

    @Column(name = "priority" ,nullable = false)
    private String priority;

    @Column(name = "progress",nullable = false)
    private Integer progress;
    //endregion

    //region Relationship Entities
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "projects_id")
     private Project project;

    @ManyToOne
    @JoinColumn(name = "categories_id")
     private Category statusCategory ;
    //endregion
}
