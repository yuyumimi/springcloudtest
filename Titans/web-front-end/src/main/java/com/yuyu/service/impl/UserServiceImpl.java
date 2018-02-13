package com.yuyu.service.impl;

import com.yuyu.entity.RcUserEntity;
import com.yuyu.repository.UserRepository;
import com.yuyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public RcUserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
