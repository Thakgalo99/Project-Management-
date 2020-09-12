package com.projectmanagement.api.repositories;

import com.projectmanagement.api.entities.Category;
import com.projectmanagement.api.entities.Status;
import com.projectmanagement.api.repositories.common.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CommonRepository<Status> {
    Status findByName(String name);
}
