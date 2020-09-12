package com.projectmanagement.api.controllers;

import com.projectmanagement.api.entities.ProjectIssue;
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

@RequestMapping(APIName.PROJECTISSUE)
@RestController
public class ProjectIssueController {

    @Autowired
    ICommonService<ProjectIssue> iCommonService;

    //region Get ProjectIssue
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProjectIssue>> getAllProjectIssues (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws Exception{

        List<ProjectIssue> projectIssueList=iCommonService.findAll(page, limit);

        if (projectIssueList.isEmpty()){
            throw  new ResourceNotFoundException(APIStatus.ERR_NO_CONTENT.getDescription());
        }

        return new ResponseEntity<>(projectIssueList, HttpStatus.OK);
    }

    @GetMapping(APIName.PROJECTISSUE_BY_PROJECTISSUE_UID)
    public ResponseEntity<ProjectIssue> getProjectIssueByUid(@PathVariable("projectissueuid") String projectissueuid) throws Exception{


        ProjectIssue projectIssue = iCommonService.findByUid(projectissueuid);

        if (projectIssue !=null) {
            return new ResponseEntity<>(projectIssue, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException(APIStatus.ERR_SESSION_NOT_FOUND.getDescription());
        }
    }
    //endregion

    //region Save ProjectIssue
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ProjectIssue> createProjectIssue(@Valid @RequestBody ProjectIssue projectIssue)throws Exception{
        //|| projectIssue.getStatus().getName().isEmpty()
        if(projectIssue.getDescription().isEmpty()){
            throw new CommonServiceException("Status or Description can't be null");
        }
        ProjectIssue newProjectIssue=iCommonService.save(projectIssue);
        return new ResponseEntity<ProjectIssue>(newProjectIssue,HttpStatus.CREATED);
    }
    //endregion

    //region Update ProjectIssue
    @PutMapping(
            path =APIName.PROJECTISSUE_BY_PROJECTISSUE_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ProjectIssue> updateProjectIssue(@Valid @RequestBody ProjectIssue projectIssue, @PathVariable("projectissueuid") String projectissueuid)throws Exception{
        if(projectIssue.getDescription().isEmpty() || projectIssue.getStatus().getName().isEmpty()){
            throw new CommonServiceException("Status or Description can't be null");
        }
        if(projectissueuid.isEmpty()){
            throw new CommonServiceException("ProjectIssue uid can't be empty");
        }

        ProjectIssue newProjectIssue=iCommonService.update(projectIssue,projectissueuid);
        return new ResponseEntity<>(newProjectIssue,HttpStatus.CREATED);
    }
    //endregion

    //region Delete ProjectIssue By Uid
    @DeleteMapping(
            path =APIName.PROJECTISSUE_BY_PROJECTISSUE_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ProjectIssue> deleteProjectIssue(@PathVariable("projectissueuid") String projectissueuid)throws Exception{

        if(projectissueuid.isEmpty()){
            throw new CommonServiceException("Project issue uid uid can't be empty");
        }

        iCommonService.deleteByUid(projectissueuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete Client All
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ProjectIssue> deleteAllProjectIssues()throws Exception{

        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //endregion
}
