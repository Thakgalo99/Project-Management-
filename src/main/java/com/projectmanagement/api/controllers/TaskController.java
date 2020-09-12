package com.projectmanagement.api.controllers;

import com.projectmanagement.api.entities.Task;
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

@RequestMapping(APIName.TASK)
@RestController
public class TaskController {
    @Autowired
    ICommonService<Task> iCommonService;

    //region Get Task
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Task>> getAllTasks (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws Exception{

        List<Task> taskList=iCommonService.findAll(page, limit);

        if (taskList.isEmpty()){
            throw  new ResourceNotFoundException(APIStatus.ERR_NO_CONTENT.getDescription());
        }

        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping(APIName.TASK_BY_TASK_UID)
    public ResponseEntity<Task> getTaskByUid(@PathVariable("taskuid") String taskuid) throws Exception{


        Task task = iCommonService.findByUid(taskuid);

        if (task !=null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException(APIStatus.ERR_SESSION_NOT_FOUND.getDescription());
        }
    }
    //endregion

    //region Save Task
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task)throws Exception{
        if(task.getName().isEmpty() || task.getDescription().isEmpty()){
            throw new CommonServiceException("Name or Description can't be null");
        }
        Task newTask=iCommonService.save(task);
        return new ResponseEntity<>(newTask,HttpStatus.CREATED);
    }
    //endregion

    //region Update Task
    @PutMapping(
            path =APIName.TASK_BY_TASK_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable("taskuid") String taskuid)throws Exception{
        if(task.getName().isEmpty() || task.getDescription().isEmpty()){
            throw new CommonServiceException("Name or Description can't be null");
        }
        if(taskuid.isEmpty()){
            throw new CommonServiceException("Task uid can't be empty");
        }

        Task newTask=iCommonService.update(task,taskuid);
        return new ResponseEntity<>(newTask,HttpStatus.CREATED);
    }
    //endregion

    //region Delete Task By Uid
    @DeleteMapping(
            path =APIName.TASK_BY_TASK_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Task> deleteTask(@PathVariable("taskuid") String taskuid)throws Exception{

        if(taskuid.isEmpty()){
            throw new CommonServiceException("Task uid can't be empty");
        }

        iCommonService.deleteByUid(taskuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete Task All
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Task> deleteAllTasks()throws Exception{

        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion
}
