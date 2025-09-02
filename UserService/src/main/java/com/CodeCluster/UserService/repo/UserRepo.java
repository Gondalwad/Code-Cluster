package com.CodeCluster.UserService.repo;

import com.CodeCluster.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
