package com.projectmanagement.api.services.Impl;

import com.projectmanagement.api.entities.Category;
import com.projectmanagement.api.entities.Notice;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.NoticeRepository;
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
public class NoticeServiceImpl implements ICommonService<Notice> {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private Utils utils;


    //region Find Notice
    @Override
    public List<Notice> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Notice> noticePage=noticeRepository.findAll(pageable);
        List<Notice> noticeList=noticePage.getContent();

        return noticeList;
    }

    @Override
    public List<Notice> findAllByCriteria(int page, int limit, String search) {
        return null;
    }

    @Override
    public Notice findById(long id) {
        Optional<Notice> notice =noticeRepository.findById(id);
        return notice.orElse(null);
    }

    @Override
    public Notice findByUid(String uid) {
        Notice notice =noticeRepository.findByUid(uid);
        return notice;
    }
    //endregion

    //region Save Notice
    @Override
    public Notice save(Notice entity) {
       entity.setUid(utils.generateStringId(32));
       Notice notice=noticeRepository.save(entity);
       return null;
    }
    //endregion

    //region Update Notice
    @Override
    public Notice update(Notice entity) {
        Notice notice=findByUid(entity.getUid());
        if(notice == null){
            throw new CommonServiceException("Notice uid not existe !");
        }
        notice.setName(entity.getName().toLowerCase());
        notice.setDescription(entity.getDescription());
        notice.setConfirmed(entity.getConfirmed());

        Notice newNotice=noticeRepository.save(notice);
        return newNotice;
    }

    @Override
    public Notice update(Notice entity, String uid) {
        Notice notice=findByUid(uid);
        if(notice == null){
            throw new CommonServiceException("Notice uid not existe !");
        }
        notice.setName(entity.getName().toLowerCase());
        notice.setDescription(entity.getDescription());
        notice.setConfirmed(entity.getConfirmed());

        Notice newNotice=noticeRepository.save(notice);
        return newNotice;
    }
    //endregion

    //region Delete Notice
    @Override
    public void delete(Notice entity) {
//        We Can Check List Before Deleted but that will take some time :)

        Integer noticeListSize=noticeRepository.findAll().size();
        if(noticeListSize <1) {
            throw new CommonServiceException("Notice list already empty !");
        }
        noticeRepository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        Notice notice=findByUid(uid);
        if(notice == null){
            throw new CommonServiceException("Notice uid not existe !");
        }
        noticeRepository.delete(notice);
    }

    @Override
    public void deleteAll() {
//        We Can Check List Before Deleted but that will take some time :)

//        Integer noticeListSize=noticeRepository.findAll().size();
//        if(noticeListSize <1) {
//            throw new CommonServiceException("Notice list already empty !");
//        }
        noticeRepository.deleteAll();
    }
    //endregion

    //region Count Notice
    @Override
    public long count() {
        return noticeRepository.count();
    }
    //endregion
}
