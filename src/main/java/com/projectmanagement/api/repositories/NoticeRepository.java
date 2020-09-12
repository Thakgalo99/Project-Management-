package com.projectmanagement.api.repositories;

import com.projectmanagement.api.entities.Notice;
import com.projectmanagement.api.repositories.common.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends CommonRepository<Notice> {
}
