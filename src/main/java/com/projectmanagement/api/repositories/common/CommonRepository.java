package com.projectmanagement.api.repositories.common;

import com.projectmanagement.api.entities.Client;
import com.projectmanagement.api.entities.common.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
//@NoRepositoryBean To exclude this Repository from being instantiated
**/
@NoRepositoryBean
public interface CommonRepository<E extends CommonEntity> extends JpaRepository<E, Long> {
    E findByUid(String uid);
}
