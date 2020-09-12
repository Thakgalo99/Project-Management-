package com.projectmanagement.api.services.common;

import com.projectmanagement.api.entities.Client;
import com.projectmanagement.api.entities.common.CommonEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICommonService<E extends CommonEntity> {

    List<E> findAll(int page,int limit);
    List<E> findAllByCriteria(int page, int limit, @Param("search") String search);
    E findById(long id);
    E findByUid(String uid);
    E save(E entity);
    E update(E entity);
    E update(E entity,String uid);
    void delete(E entity);
    void deleteByUid(String uid);
    void deleteAll();
    long count();
}