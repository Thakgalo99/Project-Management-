package com.projectmanagement.api.services.Impl;

import com.projectmanagement.api.entities.Category;
import com.projectmanagement.api.entities.Project;
import com.projectmanagement.api.entities.ProjectIssue;
import com.projectmanagement.api.entities.Status;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.CategoryRepository;
import com.projectmanagement.api.repositories.ProjectIssueRepository;
import com.projectmanagement.api.repositories.ProjectRepository;
import com.projectmanagement.api.repositories.StatusRepository;
import com.projectmanagement.api.services.common.ICommonService;
import com.projectmanagement.api.utils.Utils;
import org.modelmapper.ModelMapper;
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
public class ProjectIssueServiceImpl implements ICommonService<ProjectIssue> {

    @Autowired
    private ProjectIssueRepository projectIssueRepository;
    @Autowired
    private Utils utils;

    //region Find Project Issue
    @Override
    public List<ProjectIssue> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<ProjectIssue> projectIssuePage=projectIssueRepository.findAll(pageable);
        List<ProjectIssue> projectIssueList=projectIssuePage.getContent();

        return projectIssueList;
    }

    @Override
    public List<ProjectIssue> findAllByCriteria(int page, int limit, String search) {
        return null;
    }

    @Override
    public ProjectIssue findById(long id) {
        Optional<ProjectIssue> projectIssue = projectIssueRepository.findById(id);
        return projectIssue.orElse(null);
    }

    @Override
    public ProjectIssue findByUid(String uid) {
        ProjectIssue projectIssue = projectIssueRepository.findByUid(uid);
        return projectIssue;
    }
    //endregion

    //region Save ProjectIssue
    @Override
    public ProjectIssue save(ProjectIssue entity) {
        entity.setUid(utils.generateStringId(32));
        ProjectIssue projectIssue=projectIssueRepository.save(entity);
        return projectIssue;
    }
    //endregion

    //region Update ProjectIssue
    @Override
    public ProjectIssue update(ProjectIssue entity) {
        ProjectIssue projectIssue=findByUid(entity.getUid());
        if(projectIssue == null){
            throw new CommonServiceException("Project Issue uid not existe !");
        }
        projectIssue.setDescription(entity.getDescription());
        projectIssue.setStatus(entity.getStatus());
        projectIssue.setProject(entity.getProject());

        ProjectIssue newProjectIssue=projectIssueRepository.save(projectIssue);
        return newProjectIssue;
    }

    @Override
    public ProjectIssue update(ProjectIssue entity, String uid) {
        ProjectIssue projectIssue=findByUid(uid);
        if(projectIssue == null){
            throw new CommonServiceException("Project Issue uid not existe !");
        }
        projectIssue.setDescription(entity.getDescription());
        projectIssue.setStatus(entity.getStatus());
        projectIssue.setProject(entity.getProject());

        ProjectIssue newProjectIssue=projectIssueRepository.save(projectIssue);
        return newProjectIssue;
    }
    //endregion

    //region Delete ProjectIssue
    @Override
    public void delete(ProjectIssue entity) {
//        We Can Check List Before Deleted but that will take some time :)

        Integer projectIssueListSize=projectIssueRepository.findAll().size();
        if(projectIssueListSize <1) {
            throw new CommonServiceException("Project Issue list already empty !");
        }
        projectIssueRepository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        ProjectIssue projectIssue=findByUid(uid);
        if(projectIssue == null){
            throw new CommonServiceException("Project Issue uid not existe !");
        }
        projectIssueRepository.delete(projectIssue);
    }

    @Override
    public void deleteAll() {
//        We Can Check List Before Deleted but that will take some time :)

//        Integer projectIssueListSize=projectIssueRepository.findAll().size();
//        if(projectIssueListSize <1) {
//            throw new CommonServiceException("Project Issue list already empty !");
//        }
        projectIssueRepository.deleteAll();
    }
    //endregion

    //region Count ProjectIssue
    @Override
    public long count() {
        return projectIssueRepository.count();
    }
    //endregion
}
