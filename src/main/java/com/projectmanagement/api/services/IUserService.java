package com.projectmanagement.api.services;

import com.projectmanagement.api.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService  extends UserDetailsService {

    public List<UserDto> findAll(int page, int limit);
    public UserDto findByUid(String uid);
    UserDto signup(UserDto userDto);
    UserDto findUserByEmail(String email);
    UserDto updateProfile(UserDto userDto,String uid);
    UserDto changePassword(UserDto userDto, String newPassword);
    void deleteByUid(String uid);




}
