package com.projectmanagement.api.services.Impl;

import com.projectmanagement.api.entities.Category;
import com.projectmanagement.api.entities.Project;
import com.projectmanagement.api.entities.ProjectIssue;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.CategoryRepository;
import com.projectmanagement.api.repositories.ProjectRepository;
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
public class ProjectServiceImpl implements ICommonService<Project> {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private Utils utils;

    //region Find Project
    @Override
    public List<Project> findAll(int page, int limit) {
        if (page > 0) page -= 1;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Project> projectPage = projectRepository.findAll(pageable);
        List<Project> projectList = projectPage.getContent();

        return projectList;
    }

    @Override
    public List<Project> findAllByCriteria(int page, int limit, String search) {
        return null;
    }

    @Override
    public Project findById(long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }

    @Override
    public Project findByUid(String uid) {
        Project project = projectRepository.findByUid(uid);
        return project;
    }
    //endregion

    //region Save Project
    @Override
    public Project save(Project entity) {


        entity.setUid(utils.generateStringId(32));
        Project project = projectRepository.save(entity);
        return project;
    }
    //endregion

    //region Update Project
    @Override
    public Project update(Project entity) {
        Project project = findByUid(entity.getUid());
        if (project == null) {
            throw new CommonServiceException("Project uid not existe !");
        }

        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setClient(entity.getClient());
        project.setStatus(entity.getStatus());
        project.setDateEnd(entity.getDateEnd());

        Project newProject = projectRepository.save(project);
        return newProject;
    }

    @Override
    public Project update(Project entity, String uid) {
        Project project = findByUid(uid);
        if (project == null) {
            throw new CommonServiceException("Project uid not existe !");
        }

        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setClient(entity.getClient());
        project.setStatus(entity.getStatus());
        project.setDateEnd(entity.getDateEnd());

        Project newProject = projectRepository.save(project);
        return newProject;
    }
    //endregion

    //region Delete Project
    @Override
    public void delete(Project entity) {
//        We Can Check List Before Deleted but that will take some time :)

        Integer projectListSize = projectRepository.findAll().size();
        if (projectListSize < 1) {
            throw new CommonServiceException("Project list already empty !");
        }
        projectRepository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        Project project = findByUid(uid);
        if (project == null) {
            throw new CommonServiceException("Project uid not existe !");
        }
        projectRepository.delete(project);
    }

    @Override
    public void deleteAll() {
//        We Can Check List Before Deleted but that will take some time :)

//        Integer projectListSize=projectRepository.findAll().size();
//        if(projectListSize <1) {
//            throw new CommonServiceException("Project list already empty !");
//        }
        projectRepository.deleteAll();
    }
    //endregion

    //region Count Project
    @Override
    public long count() {
        return projectRepository.count();
    }
    //endregion
}
