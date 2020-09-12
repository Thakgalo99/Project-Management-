package com.projectmanagement.api.controllers;

import com.projectmanagement.api.entities.Notice;
import com.projectmanagement.api.entities.Project;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.exceptions.ResourceNotFoundException;
import com.projectmanagement.api.responces.APIStatus;
import com.projectmanagement.api.services.common.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(APIName.PROJECT)
@RestController
public class ProjectController {
    @Autowired
    ICommonService<Project> iCommonService;

    //region Get Project
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Project>> getAllProjects (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws Exception{

        List<Project> projectList=iCommonService.findAll(page, limit);

        if (projectList.isEmpty()){
            throw  new ResourceNotFoundException(APIStatus.ERR_NO_CONTENT.getDescription());
        }

        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @GetMapping(APIName.PROJECT_BY_PROJECT_UID)
    public ResponseEntity<Project> getProjectByUid(@PathVariable("projectuid") String projectuid) throws Exception{


        Project project = iCommonService.findByUid(projectuid);

        if (project !=null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException(APIStatus.ERR_SESSION_NOT_FOUND.getDescription());
        }
    }
    //endregion

    //region Save Project
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project)throws Exception{
        if(project.getName().isEmpty() || project.getDescription().isEmpty()){
            throw new CommonServiceException("Name or Description can't be null");
        }
        Project newProject=iCommonService.save(project);
        return new ResponseEntity<>(newProject,HttpStatus.CREATED);
    }
    //endregion

    //region Update Project
    @PutMapping(
            path =APIName.PROJECT_BY_PROJECT_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Project> updateProject(@RequestBody Project project, @PathVariable("projectuid") String projectuid)throws Exception{
        if(project.getName().isEmpty() || project.getDescription().isEmpty()){
            throw new CommonServiceException("Name or Description can't be null");
        }
        if(projectuid.isEmpty()){
            throw new CommonServiceException("Project uid can't be empty");
        }

        Project newProject=iCommonService.update(project,projectuid);
        return new ResponseEntity<>(newProject,HttpStatus.CREATED);
    }
    //endregion

    //region Delete Project By Uid
    @DeleteMapping(
            path =APIName.PROJECT_BY_PROJECT_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Project> deleteProject(@PathVariable("projectuid") String projectuid)throws Exception{

        if(projectuid.isEmpty()){
            throw new CommonServiceException("Project uid can't be empty");
        }

        iCommonService.deleteByUid(projectuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete Project All
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Project> deleteAllProjects()throws Exception{

        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //endregion
}
