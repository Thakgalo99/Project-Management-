package com.projectmanagement.api.configuration;

import com.projectmanagement.api.ProjectmanagementApplication;
import com.projectmanagement.api.entities.User;
import com.projectmanagement.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitUser implements ApplicationRunner {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private final UserRepository userRepository ;

    @Autowired
    public InitUser(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user =new User();
        user.setUid("0BjGzaXdjTKhxprXoi24GiCSyfshGGYt");
        user.setEmail("admin@gmail.com");
        user.setUsername("admin");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        userRepository.save(user);
    }
}
