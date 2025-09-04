package com.CodeCluster.UserService.repo;

import com.CodeCluster.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
