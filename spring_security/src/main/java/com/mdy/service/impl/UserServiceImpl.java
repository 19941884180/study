package com.mdy.service.impl;

import com.mdy.mapper.UserMapper;
import com.mdy.service.UserService;
import com.mdy.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserVO> findAll() {
        return userMapper.findAll();
    }
}
