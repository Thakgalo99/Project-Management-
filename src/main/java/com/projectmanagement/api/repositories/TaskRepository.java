package com.projectmanagement.api.repositories;

import com.projectmanagement.api.entities.Task;
import com.projectmanagement.api.repositories.common.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CommonRepository<Task> {
}
