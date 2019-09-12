package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User findByName(String name);
    User findById(int id);
}
