package com.projectmanagement.api.repositories;

import com.projectmanagement.api.entities.ProjectIssue;
import com.projectmanagement.api.repositories.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectIssueRepository extends CommonRepository<ProjectIssue> {
}
