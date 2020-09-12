package com.projectmanagement.api.entities;

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
@Table(name = "notices",schema = "public")
public class Notice extends CommonEntity {

    //region Fields Entity

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "confirmed")
    private Boolean confirmed;

    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation =new Date();
//endregion
}
