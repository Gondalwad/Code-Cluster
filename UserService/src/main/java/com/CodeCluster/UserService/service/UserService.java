package com.CodeCluster.UserService.service;

import com.CodeCluster.UserService.dto.UserRequestDTO;
import com.CodeCluster.UserService.dto.UserResponseDTO;
import com.CodeCluster.UserService.exception.EmailExistsException;
import com.CodeCluster.UserService.exception.UsernameExistsException;
import com.CodeCluster.UserService.mapper.UserRequestDTOToUser;
import com.CodeCluster.UserService.mapper.UserToUserResponseDTO;
import com.CodeCluster.UserService.model.User;
import com.CodeCluster.UserService.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    // create user
    public Optional<UserResponseDTO> createUser(UserRequestDTO userRequestDTO){
        User user = UserRequestDTOToUser.convert(userRequestDTO); // converts dto to user

        // checks if principal requested data already exists or not
        if(userRepo.existsByUsername(user.getUsername())){
            throw new UsernameExistsException("User already Exists By Username : " + user.getUsername());
        }
        if(userRepo.existsByEmail(user.getEmail())){
            throw new EmailExistsException("User already Exists By Email : " + user.getEmail());
        }

        try{
             User createdUser = userRepo.save(user);
             return Optional.of(UserToUserResponseDTO.convert(createdUser.getUsername()));// returns response with userResponseDTO
        } catch (Exception e) {
            log.error("Unable to create User : {}", e.getMessage());
            return Optional.empty(); // returns empty
        }



    }
}
