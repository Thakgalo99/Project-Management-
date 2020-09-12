package com.projectmanagement.api.controllers;

import com.projectmanagement.api.entities.Status;
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

@RequestMapping(APIName.STATUS)
@RestController
public class StatusController {

    @Autowired
    ICommonService<Status> iCommonService;

    //region Get Status
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Status>> getAllStatus (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws Exception{

        List<Status> statusList=iCommonService.findAll(page, limit);

        if (statusList.isEmpty()){
            throw  new ResourceNotFoundException(APIStatus.ERR_NO_CONTENT.getDescription());
        }

        return new ResponseEntity<>(statusList, HttpStatus.OK);
    }

    @GetMapping(APIName.STATUS_BY_STATUS_UID)
    public ResponseEntity<Status> getStatusByUid(@PathVariable("statusuid") String statusuid) throws Exception{


        Status status = iCommonService.findByUid(statusuid);

        if (status !=null) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException(APIStatus.ERR_SESSION_NOT_FOUND.getDescription());
        }
    }
    //endregion

    //region Save Status
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Status> createStatus(@RequestBody Status status)throws Exception{
        if(status.getName().isEmpty()){
            throw new CommonServiceException("Name can't be null");
        }
        Status newStatus=iCommonService.save(status);
        return new ResponseEntity<>(newStatus,HttpStatus.CREATED);
    }
    //endregion

    //region Update Status
    @PutMapping(
            path =APIName.STATUS_BY_STATUS_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Status> updateStatus(@RequestBody Status status,@PathVariable("statusuid") String statusUid)throws Exception{
        if(status.getName().isEmpty()){
            throw new CommonServiceException("Name can't be null");
        }
        if(statusUid.isEmpty()){
            throw new CommonServiceException("Status uid can't be empty");
        }

        Status newStatus=iCommonService.update(status,statusUid);
        return new ResponseEntity<>(newStatus,HttpStatus.CREATED);
    }
    //endregion

    //region Delete Status By Uid
    @DeleteMapping(
            path =APIName.STATUS_BY_STATUS_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Status> deleteStatus(@PathVariable("statusuid") String statusUid)throws Exception{

        if(statusUid.isEmpty()){
            throw new CommonServiceException("Status uid can't be empty");
        }

        iCommonService.deleteByUid(statusUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete Status All
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Status> deleteAllStatus()throws Exception{

        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //endregion
}
