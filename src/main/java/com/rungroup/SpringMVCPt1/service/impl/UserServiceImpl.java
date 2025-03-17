package com.rungroup.SpringMVCPt1.service.impl;

import com.rungroup.SpringMVCPt1.dto.RegistrationDto;
import com.rungroup.SpringMVCPt1.models.Role;
import com.rungroup.SpringMVCPt1.models.UserEntity;
import com.rungroup.SpringMVCPt1.repository.RoleRepository;
import com.rungroup.SpringMVCPt1.repository.UserRepository;
import com.rungroup.SpringMVCPt1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPassword(registrationDto.getPassword());

        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Arrays.asList(role));

        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
