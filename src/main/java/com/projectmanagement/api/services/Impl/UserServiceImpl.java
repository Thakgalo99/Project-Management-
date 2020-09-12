package com.projectmanagement.api.services.Impl;

import com.projectmanagement.api.dto.UserDto;
import com.projectmanagement.api.entities.User;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.UserRepository;
import com.projectmanagement.api.services.IUserService;
import com.projectmanagement.api.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Utils utils;

    @Override
    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new CommonServiceException("User with this email: " + email + " not found.");
    }

    @Override
    public List<UserDto> findAll(int page, int limit) {
        if (page > 0) page -= 1;
        List<UserDto> userDtoList = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<User> userEntities = userRepository.findAll(pageable);

        List<User> entityList = userEntities.getContent();

        for (User userEntity : entityList) {
            ModelMapper modelMapper = new ModelMapper();
            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto findByUid(String uid) {
        User userEntity = userRepository.findByUid(uid);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto=modelMapper.map(userEntity,UserDto.class);

        return userDto;
    }

    @Override
    public UserDto signup(UserDto userDto) {
        User getUser = userRepository.findByEmail(userDto.getEmail());

        if (getUser != null) {
            throw new CommonServiceException("User already existe !");
        }

        String randomUid=utils.generateStringId(32);

//        userDto.getUserProfile().setUid(randomUid);
//        userDto.getUserProfile().setUser(userDto);

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);

        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setUid(randomUid);

        User createUser = userRepository.save(user);

        UserDto copiedUser = modelMapper.map(createUser, UserDto.class);

        return copiedUser;
    }

    @Override
    public UserDto updateProfile(UserDto userDto,String uid) {
        User userEntity = userRepository.findByUid(uid);
        if (userEntity == null)
            throw new CommonServiceException("User with this email: " + userEntity.getEmail() + " not found.");

        Integer getlastModified=userEntity.getTime_modified_number()+1;
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setTime_modified_number(getlastModified);
        userEntity.setDate_modified(new Date());
        User updateUser = userRepository.save(userEntity);

        UserDto copiedUser = modelMapper.map(updateUser, UserDto.class);
        return copiedUser;
    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        return null;
    }

    @Override
    public void deleteByUid(String uid) {
        User userEntity = userRepository.findByUid(uid);
        if (userEntity == null) throw new UsernameNotFoundException("Email not found");
        userRepository.delete(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("User with this email: " + user.getEmail() +" not found ... please try anthor one.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>()) ;
    }
}
