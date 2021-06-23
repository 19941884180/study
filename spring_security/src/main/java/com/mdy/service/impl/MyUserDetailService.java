package com.mdy.service.impl;

import com.mdy.mapper.RoleMapper;
import com.mdy.mapper.UserMapper;
import com.mdy.vo.RoleVO;
import com.mdy.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserVO userVO = userMapper.findByUserName(s);
        if (null == userVO) {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        List<RoleVO> roleVOS = roleMapper.findListByUserId(userVO.getId());
        if (!CollectionUtils.isEmpty(roleVOS)) {
            roleVOS.forEach(roleVO -> {
                authorityList.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
            });
        }
        return new User(userVO.getUserName(), userVO.getPassword(), authorityList);
    }
}
