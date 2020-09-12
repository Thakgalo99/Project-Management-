package com.projectmanagement.api.services.Impl;

import com.projectmanagement.api.entities.Category;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.CategoryRepository;
import com.projectmanagement.api.services.common.ICommonService;
import com.projectmanagement.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CategoryServiceImpl implements ICommonService<Category> {

//    @Autowired
//    private CommonRepository<Category> commonRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Utils utils;

    //region Find Category
    @Override
    public List<Category> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Category> categoryPage=categoryRepository.findAll(pageable);
        List<Category> categoryList=categoryPage.getContent();

        return categoryList;
    }

    @Override
    public List<Category> findAllByCriteria(int page, int limit, String search) {
        return null;
    }

    @Override
    public Category findById(long id) {
        Optional <Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category findByUid(String uid) {
        Category category = categoryRepository.findByUid(uid);
        return category;
    }
    //endregion

    //region Save Category
    @Override
    public Category save(Category entity) {
        String categoryName=(entity.getName().trim()).toLowerCase();

        Category getCategory=categoryRepository.findByName(categoryName);
        if(getCategory != null){
           throw new CommonServiceException("Category already existe !");
        }
        entity.setName(entity.getName().toLowerCase());
        entity.setUid(utils.generateStringId(32));
        Category category=categoryRepository.save(entity);
        return category;
    }
    //endregion

    //region Update Category
    @Override
    public Category update(Category entity) {
        Category category=findByUid(entity.getUid());
        if(category == null){
            throw new CommonServiceException("Category uid not existe !");
        }
        category.setName(entity.getName().toLowerCase());
        category.setDescription(entity.getDescription());

        Category newCategory=categoryRepository.save(category);
        return newCategory;
    }

    @Override
    public Category update(Category entity, String uid) {
        Category category=findByUid(uid);
        if(category == null){
            throw new CommonServiceException("Category uid not existe !");
        }
        category.setName(entity.getName().toLowerCase());
        category.setDescription(entity.getDescription());

        Category newCategory=categoryRepository.save(category);
        return newCategory;
    }
    //endregion

    //region Delete Category
    @Override
    public void delete(Category entity) {
//        We Can Check List Before Deleted but that will take some time :)

        Integer categoryListSize=categoryRepository.findAll().size();
        if(categoryListSize <1) {
            throw new CommonServiceException("Category list already empty !");
        }
        categoryRepository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        Category category=findByUid(uid);
        if(category == null){
            throw new CommonServiceException("Category uid not existe !");
        }
        categoryRepository.delete(category);
    }

    @Override
    public void deleteAll() {
//        We Can Check List Before Deleted but that will take some time :)

//        Integer categoryListSize=categoryRepository.findAll().size();
//        if(categoryListSize <1) {
//            throw new CommonServiceException("Category list already empty !");
//        }
        categoryRepository.deleteAll();
    }
    //endregion

    //region Count Category
    @Override
    public long count() {
        return categoryRepository.count();
    }
    //endregion
}
