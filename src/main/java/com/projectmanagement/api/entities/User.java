package com.projectmanagement.api.entities;

import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "users",schema = "public")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class User extends CommonEntity {

    //region Fields Entity

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "datecreated")
    @Temporal(TemporalType.DATE)
    private Date date_created =new Date();

    @Column(name = "datemodified")
    @Temporal(TemporalType.DATE)
    private Date date_modified;

    @Column(name = "datemodifiednumber")
    private int time_modified_number;

    @Column(name = "isTokenExpired")
    private boolean isTokenExpired=false;

    @Column(name = "isAccountNonExpired")
    private boolean isAccountNonExpired=true;

    @Column(name = "isAccountNonLocked")
    private boolean isAccountNonLocked=true;

    @Column(name = "isCredentialsNonExpired")
    private boolean isCredentialsNonExpired=true;

    @Column(name = "isEnabled")
    private boolean isEnabled=true;
    //endregion

    //region Relationship Entities
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Task> tasks;

//    @OneToOne(fetch = FetchType.EAGER,mappedBy = "user",cascade = CascadeType.ALL)
//    private UserProfile userProfile;
    //endregion

}
