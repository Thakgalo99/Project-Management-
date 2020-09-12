package com.projectmanagement.api.controllers;

import com.projectmanagement.api.entities.Category;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.exceptions.ResourceNotFoundException;
import com.projectmanagement.api.responces.APIStatus;
import com.projectmanagement.api.services.common.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(APIName.CATEGORY)
@RestController
public class CategoryController {

    @Autowired
    ICommonService<Category> iCommonService;

    //region Get Category
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Category>> getAllCategories (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws Exception{

        List<Category> categoryList=iCommonService.findAll(page, limit);

        if (categoryList.isEmpty()){
            throw  new ResourceNotFoundException(APIStatus.ERR_NO_CONTENT.getDescription());
        }

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping(APIName.CATEGORY_BY_CATEGORY_UID)
    public ResponseEntity<Category> getCategoryByUid(@PathVariable("categoryuid") String categoryuid) throws Exception{


        Category category = iCommonService.findByUid(categoryuid);

        if (category !=null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException(APIStatus.ERR_SESSION_NOT_FOUND.getDescription());
        }
    }
    //endregion

    //region Save Category
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<Category> createCategory(@RequestBody Category category)throws Exception{
        if(category.getName().isEmpty()){
            throw new CommonServiceException("Name can't be null");
        }
        Category newCategory=iCommonService.save(category);
        return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }
    //endregion

    //region Update Category
    @PutMapping(
            path =APIName.CATEGORY_BY_CATEGORY_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Category> updateCategory(@RequestBody Category category,@PathVariable("categoryuid") String categoryUid)throws Exception{
        if(category.getName().isEmpty()){
            throw new CommonServiceException("Name can't be null");
        }
        if(categoryUid.isEmpty()){
            throw new CommonServiceException("Category uid can't be empty");
        }

        Category newCategory=iCommonService.update(category,categoryUid);
        return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }
    //endregion

    //region Delete Category By Uid
    @DeleteMapping(
            path =APIName.CATEGORY_BY_CATEGORY_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Category> deleteCategory(@PathVariable("categoryuid") String categoryUid)throws Exception{

        if(categoryUid.isEmpty()){
            throw new CommonServiceException("Category uid can't be empty");
        }

        iCommonService.deleteByUid(categoryUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete Category All
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Category> deleteAllCategory()throws Exception{

        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //endregion
}
