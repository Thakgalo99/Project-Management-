package com.projectmanagement.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "clients",schema = "public")
public class Client extends CommonEntity {

    //region Fields Entity

    @Column(name = "firstName",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;

    @Column(name = "companyName",nullable = false)
    private String companyName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    //endregion

    //region Relationship Entities
//     @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
//     private List<Project> project;
      //endregion
}
