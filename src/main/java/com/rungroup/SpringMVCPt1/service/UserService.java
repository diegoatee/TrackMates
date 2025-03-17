package com.rungroup.SpringMVCPt1.service;

import com.rungroup.SpringMVCPt1.dto.RegistrationDto;
import com.rungroup.SpringMVCPt1.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
