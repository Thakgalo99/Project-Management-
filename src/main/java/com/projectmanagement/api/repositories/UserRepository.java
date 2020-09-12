package com.projectmanagement.api.repositories;

import com.projectmanagement.api.entities.User;
import com.projectmanagement.api.repositories.common.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<User> {
    User findByEmail(String email);
    User findByUsername(String userName);
    User findByUid(String uid);
}
