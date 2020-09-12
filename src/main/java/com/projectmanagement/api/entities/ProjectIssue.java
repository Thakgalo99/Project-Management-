package com.projectmanagement.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projectmanagement.api.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projectissues", schema = "public")
public class ProjectIssue extends CommonEntity {

    //region Fields Entity
    @Column(name = "description")
    private String description;

    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation = new Date();
    //endregion

    //region Relationship Entities
    @ManyToOne
    @JoinColumn(name = "statuses_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "projectes_id")
    private Project project;

    //endregion
}
