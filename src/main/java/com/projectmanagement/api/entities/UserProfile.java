package com.projectmanagement.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projectmanagement.api.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "userprofiles",schema = "public")
public class UserProfile extends CommonEntity {

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "postalcode")
    private String postal_code;

    @Column(name = "skills")
    private String skills;

    @Column(name = "facebook_acount")
    private String facebook_acount;

    @Column(name = "twitter_acount")
    private String twitter_acount;

    @Column(name = "linkedin_acount")
    private String linkedin_acount;

    @Column(name = "youtube_acount")
    private String youtube_acount;

    @Column(name = "portfolio")
    private String portfolio;

    @Column(name = "education")
    private String education;

    @Column(name = "urlImage")
    private String urlImage;

    @Column(name = "notes")
    private String notes;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

}
