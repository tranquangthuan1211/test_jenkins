package com.example.demo.service;

import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@Data
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User createUser(UserCreateRequest request){
        System.out.println(request);
        if(request.getPassword().isEmpty()){
            throw new AppException(ErrorCode.USER_NOT_EXITS);
        }
        User newUser = userMapper.toUser(request);
        HashSet<String>roles = new HashSet<>();
        roles.add(Role.User.name());
        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }
    public User getUserById(String id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) throw new AppException(ErrorCode.USER_NOT_EXITS);

        return user.get();
    }
}
