package com.projectmanagement.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "categories",schema = "public")
public class Category extends CommonEntity {

    //region Fields Entity

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
//endregion

    //region Relationship Entities
//    @OneToMany(mappedBy = "statusCategory",cascade = CascadeType.ALL)
//    private List<Task> taskList;
    //endregion
}
