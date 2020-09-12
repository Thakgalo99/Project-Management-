package com.projectmanagement.api.entities;

import com.fasterxml.jackson.annotation.*;
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
@Table(name = "projects", schema = "public")
public class Project extends CommonEntity {

    //region Fields Entity

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Column(name = "dateEnd")
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    @Column(name = "price")
    private Double price;

    //endregion

    //region Relationship Entities
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ProjectIssue> projectIssueList;


    @ManyToOne
    @JoinColumn(name = "statuses_id")
    private Status status;

//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client client;
    //endregion
}
