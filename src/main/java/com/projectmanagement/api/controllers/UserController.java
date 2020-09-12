package com.projectmanagement.api.controllers;

import com.projectmanagement.api.dto.UserDto;
import com.projectmanagement.api.entities.Client;
import com.projectmanagement.api.entities.Task;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.exceptions.ResourceNotFoundException;
import com.projectmanagement.api.requests.UserRequest;
import com.projectmanagement.api.responces.APIStatus;
import com.projectmanagement.api.responces.UserResponce;
import com.projectmanagement.api.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(APIName.USER)
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    //region Get User
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserResponce>> getAllClients(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15", required = false) int limit) throws Exception {

        List<UserResponce> userResponce = new ArrayList<>();

        List<UserDto> users = userService.findAll(page, limit);

        for (UserDto dto : users) {
            ModelMapper modelMapper = new ModelMapper();
            UserResponce userResponceMap = modelMapper.map(dto, UserResponce.class);
            userResponce.add(userResponceMap);
        }

        return new ResponseEntity<List<UserResponce>>(userResponce, HttpStatus.OK);
    }


    @GetMapping(APIName.USER_BY_USER_UID)
    public ResponseEntity<UserResponce> getUserByUid(@PathVariable("useruid") String userid) throws Exception {

        UserDto userDto = userService.findByUid(userid);
        ModelMapper modelMapper = new ModelMapper();
        UserResponce userResponceMap = modelMapper.map(userDto, UserResponce.class);

        return new ResponseEntity<>(userResponceMap, HttpStatus.OK);
    }
    //endregion

    //region Create User
    @PostMapping
    public ResponseEntity<UserResponce> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

        if (userRequest.getUsername().isEmpty())
            throw new CommonServiceException("Username can't be null");

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        UserDto createUser = userService.signup(userDto);

        UserResponce userResponce = modelMapper.map(createUser, UserResponce.class);

        return new ResponseEntity<UserResponce>(userResponce, HttpStatus.CREATED);
    }
    //endregion

    //region Uodate user
    @PutMapping(path = APIName.USER_BY_USER_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserResponce> updateUser(@PathVariable("useruid") String useruid, @RequestBody UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);


        UserDto updateUser = userService.updateProfile(userDto, useruid);

        UserResponce userResponce = new UserResponce();
        BeanUtils.copyProperties(updateUser, userResponce);

        return new ResponseEntity<UserResponce>(userResponce, HttpStatus.ACCEPTED);
    }
    //endregion

    //region Delete User By Uid
    @DeleteMapping(
            path =APIName.USER_BY_USER_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Task> deleteTask(@PathVariable("useruid") String useruid)throws Exception{

        if(useruid.isEmpty()){
            throw new CommonServiceException("User uid can't be empty");
        }

        userService.deleteByUid(useruid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion


}
