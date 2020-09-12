package com.projectmanagement.api.services.Impl;

import com.projectmanagement.api.entities.Status;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.StatusRepository;
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
public class StatusServiceImpl implements ICommonService<Status> {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private Utils utils;

    //region Find Status
    @Override
    public List<Status> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Status> statusPage=statusRepository.findAll(pageable);

        List<Status> statusList=statusPage.getContent();
        return statusList;
    }

    @Override
    public List<Status> findAllByCriteria(int page, int limit, String search) {
        return null;
    }

    @Override
    public Status findById(long id) {
        Optional<Status> status = statusRepository.findById(id);
        return status.orElse(null);
    }

    @Override
    public Status findByUid(String uid) {
        Status status = statusRepository.findByUid(uid);
        return status;
    }
    //endregion

    //region Status
    @Override
    public Status save(Status entity) {
        String statusName=(entity.getName().trim()).toLowerCase();

        Status getStatus=statusRepository.findByName(statusName);

        if(getStatus != null){
            throw new CommonServiceException("status already existe !");
        }
        entity.setName(entity.getName().toLowerCase());
        entity.setUid(utils.generateStringId(32));

        Status status=statusRepository.save(entity);
        return status;
    }
    //endregion

    //region Update Status
    @Override
    public Status update(Status entity) {
        Status status=findByUid(entity.getUid());
        if(status == null){
            throw new CommonServiceException("Status uid not existe !");
        }
        status.setName(entity.getName().toLowerCase());
        status.setDescription(entity.getDescription());

        Status newStatus=statusRepository.save(status);
        return newStatus;
    }

    @Override
    public Status update(Status entity, String uid) {
        Status status=findByUid(uid);
        if(status == null){
            throw new CommonServiceException("status uid not existe !");
        }
        status.setName(entity.getName().toLowerCase());
        status.setDescription(entity.getDescription());

        Status newStatus=statusRepository.save(status);
        return newStatus;
    }
    //endregion

    //region Delete Status
    @Override
    public void delete(Status entity) {
        List<Status> statusList=statusRepository.findAll();
        if(statusList.isEmpty()) {
            throw new CommonServiceException("Status list already empty !");
        }
        statusRepository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        Status status=findByUid(uid);
        if(status == null){
            throw new CommonServiceException("Status uid not existe !");
        }
        statusRepository.delete(status);
    }

    @Override
    public void deleteAll() {
        statusRepository.deleteAll();
    }
    //endregion

    //region Count Status
    @Override
    public long count() {
        return statusRepository.count();
    }
    //endregion
}
