package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceMyBatis {
    private final UserMapper userMapper;

    public UserServiceMyBatis(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUser(Long id) { return userMapper.findById(id); }
    public List<User> getAllUsers() { return userMapper.findAll(); }
    public void createUser(User user) { userMapper.insert(user); }
    public void updateUser(User user) { userMapper.update(user); }
    public void deleteUser(Long id) { userMapper.delete(id); }
}
