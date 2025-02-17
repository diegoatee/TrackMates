package com.rungroup.SpringMVCPt1.repository;

import com.rungroup.SpringMVCPt1.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);
}
