package com.projectmanagement.api.repositories;

import com.projectmanagement.api.entities.User;
import com.projectmanagement.api.entities.UserProfile;
import com.projectmanagement.api.repositories.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CommonRepository<UserProfile> {

}
