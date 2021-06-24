package com.mdy.service;

import com.mdy.vo.UserVO;

import java.util.List;


public interface UserService {
    List<UserVO> findAll();
}
