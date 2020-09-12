package com.projectmanagement.api.controllers;

import com.projectmanagement.api.entities.Notice;
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

@RequestMapping(APIName.NOTICE)
@RestController
public class NoticeController {

    @Autowired
    ICommonService<Notice> iCommonService;

    //region Get Notice
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Notice>> getAllNotices (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws Exception{

        List<Notice> noticeList=iCommonService.findAll(page, limit);

        if (noticeList.isEmpty()){
            throw  new ResourceNotFoundException(APIStatus.ERR_NO_CONTENT.getDescription());
        }

        return new ResponseEntity<>(noticeList, HttpStatus.OK);
    }

    @GetMapping(APIName.NOTICE_BY_NOTICE_UID)
    public ResponseEntity<Notice> getNoticeByUid(@PathVariable("noticeuid") String noticeuid) throws Exception{


        Notice notice = iCommonService.findByUid(noticeuid);

        if (notice !=null) {
            return new ResponseEntity<>(notice, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException(APIStatus.ERR_SESSION_NOT_FOUND.getDescription());
        }
    }
    //endregion

    //region Save Notice
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Notice> createNotice(@Valid @RequestBody Notice notice)throws Exception{
        if(notice.getName().isEmpty() || notice.getDescription().isEmpty()){
            throw new CommonServiceException("Name or Description can't be null");
        }
        Notice newNotice=iCommonService.save(notice);
        return new ResponseEntity<>(newNotice,HttpStatus.CREATED);
    }
    //endregion

    //region Update Notice
    @PutMapping(
            path =APIName.NOTICE_BY_NOTICE_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Notice> updateNotice(@RequestBody Notice notice, @PathVariable("noticeuid") String noticeuid)throws Exception{
        if(notice.getName().isEmpty() || notice.getDescription().isEmpty()){
            throw new CommonServiceException("Name or Description can't be null");
        }
        if(noticeuid.isEmpty()){
            throw new CommonServiceException("Notice uid can't be empty");
        }

        Notice newNotice=iCommonService.update(notice,noticeuid);
        return new ResponseEntity<>(newNotice,HttpStatus.CREATED);
    }
    //endregion

    //region Delete Notice By Uid
    @DeleteMapping(
            path =APIName.NOTICE_BY_NOTICE_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Notice> deleteNotice(@PathVariable("noticeuid") String noticeuid)throws Exception{

        if(noticeuid.isEmpty()){
            throw new CommonServiceException("Client uid can't be empty");
        }

        iCommonService.deleteByUid(noticeuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete Client All
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Notice> deleteAllNotices()throws Exception{

        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //endregion
}
