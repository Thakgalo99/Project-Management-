package com.projectmanagement.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanagement.api.entities.UserProfile;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.exceptions.ResourceNotFoundException;
import com.projectmanagement.api.responces.APIStatus;
import com.projectmanagement.api.services.common.ICommonService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequestMapping(APIName.USERPROFILE)
@RestController
public class UserProfileController {

    @Autowired
    ICommonService<UserProfile> iCommonService;

    @Autowired
    public ServletContext context;

    //region Get User Profil
    @GetMapping(APIName.USERPROFILE_BY_USER_UID)
    public ResponseEntity<UserProfile> getUserProfileByUid(@PathVariable("userprofileuid") String userprofileuid) throws Exception{


        UserProfile userProfile = iCommonService.findByUid(userprofileuid);

        if (userProfile !=null) {
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException(APIStatus.ERR_SESSION_NOT_FOUND.getDescription());
        }
    }
    //endregion

    //region Get User Profil Image
    @GetMapping(path = APIName.USERPROFILE_IMAGE_BY_USER_UID)
    public byte[] getPhoto(@PathVariable("userprofileuid") String userprofileuid) throws Exception{
        UserProfile userProfile   = iCommonService.findByUid(userprofileuid);

        if(userProfile == null){
            throw new CommonServiceException("User profile uid not found");
        }
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+userProfile.getUrlImage()));
    }
    //endregion

    //region Save UserProfile
    @PostMapping
    public ResponseEntity<?> createUserProfile(@RequestParam("file") MultipartFile file,  @Valid @RequestParam("userProfile") String userProfile)throws Exception{

        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("---Folder Was Created---");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }

        UserProfile userProfileMapper = new ObjectMapper().readValue(userProfile, UserProfile.class);

        userProfileMapper.setUrlImage(newFileName);
        UserProfile newUserProfile=iCommonService.save(userProfileMapper);
        return new ResponseEntity<>("UserProfile was saved",HttpStatus.CREATED);
    }
    //endregion

    //region Update UserProfile
    @PutMapping(path =APIName.USERPROFILE_BY_USER_UID)
    public ResponseEntity<?> updateUserProfile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userProfile") String userProfile,
            @PathVariable("userprofileuid") String userprofileuid)throws Exception{

        if(userprofileuid.isEmpty()){
            throw new CommonServiceException("User profile uid can't be empty");
        }

        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("---Folder Was Created---");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }

        UserProfile userProfileMapper = new ObjectMapper().readValue(userProfile, UserProfile.class);

        userProfileMapper.setUrlImage(newFileName);
        UserProfile newUserProfile=iCommonService.update(userProfileMapper,userprofileuid);
        return new ResponseEntity<>("UserProfile was updated",HttpStatus.CREATED);
    }
    //endregion
}
