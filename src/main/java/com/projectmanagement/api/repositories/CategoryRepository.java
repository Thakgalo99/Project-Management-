package com.projectmanagement.api.repositories;

import com.projectmanagement.api.entities.Category;
import com.projectmanagement.api.repositories.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CommonRepository<Category> {
    Category findByName(String name);
}
