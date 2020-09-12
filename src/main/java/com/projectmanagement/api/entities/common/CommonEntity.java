package com.projectmanagement.api.entities.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
//@Inheritance
public class CommonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uid",nullable = false)
    private String uid;
}
