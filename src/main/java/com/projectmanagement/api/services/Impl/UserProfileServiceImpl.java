package com.projectmanagement.api.services.Impl;

import com.projectmanagement.api.entities.UserProfile;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.UserProfileRepository;
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
public class UserProfileServiceImpl implements ICommonService<UserProfile> {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private Utils utils;



    //region Find Client
    @Override
    public List<UserProfile> findAll(int page, int limit) {
        if(page>0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<UserProfile> userProfilePage=userProfileRepository.findAll(pageable);

        List<UserProfile> userProfileList=userProfilePage.getContent();
        return userProfileList;
    }

    @Override
    public UserProfile findById(long id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        return userProfile.orElse(null);
    }

    @Override
    public UserProfile findByUid(String uid) {
        UserProfile userProfile = userProfileRepository.findByUid(uid);
        return userProfile;
    }
    //endregion

    //region Save Client
    @Override
    public UserProfile save(UserProfile entity) {
        UserProfile getUserProfile =userProfileRepository.findByUid(entity.getUid());
        if(getUserProfile != null){
            throw new CommonServiceException("You have already created your profile !");
        }
        UserProfile userProfile=userProfileRepository.save(entity);
        return userProfile;
    }
    //endregion

    //region Update Client
    @Override
    public UserProfile update(UserProfile entity) {
        UserProfile userProfile=findByUid(entity.getUid());
        if(userProfile == null){
            throw new CommonServiceException("User Profile uid not existe !");
        }

        userProfile.setFirstName(entity.getFirstName());
        userProfile.setLastName(entity.getLastName());
        userProfile.setGender(entity.getGender());
        userProfile.setBirthDate(entity.getBirthDate());
        userProfile.setAddress(entity.getAddress());
        userProfile.setCity(entity.getCity());
        userProfile.setStreet(entity.getStreet());
        userProfile.setPostal_code(entity.getPostal_code());
        userProfile.setSkills(entity.getSkills());
        userProfile.setFacebook_acount(entity.getFacebook_acount());
        userProfile.setTwitter_acount(entity.getTwitter_acount());
        userProfile.setLinkedin_acount(entity.getLinkedin_acount());
        userProfile.setEducation(entity.getEducation());
        userProfile.setUrlImage(entity.getUrlImage());
        userProfile.setNotes(entity.getNotes());

        UserProfile newUserProfile=userProfileRepository.save(userProfile);

        return newUserProfile;
    }

    @Override
    public UserProfile update(UserProfile entity, String uid) {
        UserProfile userProfile=findByUid(uid);
        if(userProfile == null){
            throw new CommonServiceException("User Profile uid not existe !");
        }

        userProfile.setFirstName(entity.getFirstName());
        userProfile.setLastName(entity.getLastName());
        userProfile.setGender(entity.getGender());
        userProfile.setBirthDate(entity.getBirthDate());
        userProfile.setAddress(entity.getAddress());
        userProfile.setCity(entity.getCity());
        userProfile.setStreet(entity.getStreet());
        userProfile.setPostal_code(entity.getPostal_code());
        userProfile.setSkills(entity.getSkills());
        userProfile.setFacebook_acount(entity.getFacebook_acount());
        userProfile.setTwitter_acount(entity.getTwitter_acount());
        userProfile.setLinkedin_acount(entity.getLinkedin_acount());
        userProfile.setYoutube_acount(entity.getYoutube_acount());
        userProfile.setPortfolio(entity.getPortfolio());
        userProfile.setEducation(entity.getEducation());
        userProfile.setUrlImage(entity.getUrlImage());
        userProfile.setNotes(entity.getNotes());

        UserProfile newUserProfile=userProfileRepository.save(userProfile);

        return newUserProfile;
    }
    //endregion

    //region Delete Client
    @Override
    public void delete(UserProfile entity) {
        Integer UserProfileListSize=userProfileRepository.findAll().size();

        if(UserProfileListSize <1) {
            throw new CommonServiceException("User Profile list already empty !");
        }
        userProfileRepository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        UserProfile userProfile=findByUid(uid);

        if(userProfile == null){
            throw new CommonServiceException("User Profile uid not existe !");
        }
        userProfileRepository.delete(userProfile);
    }

    @Override
    public void deleteAll() {
//        Integer clientListSize=clientRepository.findAll().size();
//
//        if(clientListSize <1) {
//            throw new CommonServiceException("Client list already empty !");
//        }
        userProfileRepository.deleteAll();
    }
    //endregion

    //region Count Client
    @Override
    public long count() {
        return userProfileRepository.count();
    }


    @Override
    public List<UserProfile> findAllByCriteria(int page, int limit, String search) {
        if(page>0) page -=1;

        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<UserProfile> userProfilePage =null;
        String getSearchKeyWord=search;
        if(getSearchKeyWord==null){
            userProfilePage=userProfileRepository.findAll(pageable);
        }
        else if(getSearchKeyWord.isEmpty()){
            userProfilePage=userProfileRepository.findAll(pageable);
        }

        List<UserProfile> userProfileList=userProfilePage.getContent();
        return userProfileList;
    }
    //endregion
}
