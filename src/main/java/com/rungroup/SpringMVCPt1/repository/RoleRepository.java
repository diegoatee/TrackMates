package com.rungroup.SpringMVCPt1.repository;

import com.rungroup.SpringMVCPt1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
