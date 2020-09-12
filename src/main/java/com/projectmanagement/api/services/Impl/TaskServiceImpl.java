package com.projectmanagement.api.services.Impl;

import com.projectmanagement.api.entities.Category;
import com.projectmanagement.api.entities.Task;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.TaskRepository;
import com.projectmanagement.api.services.common.ICommonService;
import com.projectmanagement.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TaskServiceImpl implements ICommonService<Task> {

    @Autowired
    private TaskRepository taskRepository ;

    @Autowired
    private Utils utils;

    //region Find Task
    @Override
    public List<Task> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Task> taskPage =taskRepository.findAll(pageable);
        List<Task> taskList =taskPage.getContent();

        return taskList;
    }

    @Override
    public List<Task> findAllByCriteria(int page, int limit, String search) {
        return null;
    }
    //endregion

    //region Task
    @Override
    public Task findById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public Task findByUid(String uid) {
        Task task = taskRepository.findByUid(uid);
        return task;
    }
    //endregion

    //region Save Task
    @Override
    public Task save(Task entity) {
        entity.setUid(utils.generateStringId(32));
        Task task=taskRepository.save(entity);
        return task;
    }
    //endregion

    //region Update Task
    @Override
    public Task update(Task entity) {
        Task task=findByUid(entity.getUid());
        if(task == null){
            throw new CommonServiceException("Task uid not existe !");
        }
        task.setName(entity.getName().toLowerCase());
        task.setDescription(entity.getDescription());
        task.setStartDate(entity.getStartDate());
        task.setEndDate(entity.getEndDate());
        task.setIsActivePasive(entity.getIsActivePasive());
        task.setPriority(entity.getPriority());
        task.setOwner(entity.getOwner());
        task.setStatusCategory(entity.getStatusCategory());
        task.setProgress(entity.getProgress());

        Task newTask=taskRepository.save(task);
        return newTask;
    }

    @Override
    public Task update(Task entity, String uid) {
        Task task=findByUid(uid);
        if(task == null){
            throw new CommonServiceException("Task uid not existe !");
        }
        task.setName(entity.getName().toLowerCase());
        task.setDescription(entity.getDescription());
        task.setPriority(entity.getPriority());
        task.setIsActivePasive(entity.getIsActivePasive());
        task.setPriority(entity.getPriority());
        task.setOwner(entity.getOwner());
        task.setStatusCategory(entity.getStatusCategory());
        task.setProgress(entity.getProgress());

        Task newTask=taskRepository.save(task);
        return newTask;
    }
    //endregion

    //region Delete Task
    @Override
    public void delete(Task entity) {
//        We Can Check List Before Deleted but that will take some time :)

        Integer taskListSize=taskRepository.findAll().size();
        if(taskListSize <1) {
            throw new CommonServiceException("Task list already empty !");
        }
        taskRepository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        Task task=findByUid(uid);
        if(task == null){
            throw new CommonServiceException("Task uid not existe !");
        }
        taskRepository.delete(task);
    }

    @Override
    public void deleteAll() {
//        We Can Check List Before Deleted but that will take some time :)
//
//        Integer taskListSize=taskRepository.findAll().size();
//        if(taskListSize <1) {
//            throw new CommonServiceException("Task list already empty !");
//        }
        taskRepository.deleteAll();
    }
    //endregion

    //region Count Task
    @Override
    public long count() {
        return taskRepository.count();
    }
    //endregion
}
