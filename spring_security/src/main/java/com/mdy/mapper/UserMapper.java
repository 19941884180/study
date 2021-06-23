package com.mdy.mapper;

import com.mdy.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMapper extends JpaRepository<UserVO,String> {
    List<UserVO> findAll();
}
