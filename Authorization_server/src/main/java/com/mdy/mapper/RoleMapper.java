package com.mdy.mapper;

import com.mdy.vo.RoleVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleMapper extends JpaRepository<RoleVO, String> {

    @Query(value = "select * from role where id in (SELECT role_id FROM user_role WHERE user_id = ?1 )",nativeQuery = true)
    List<RoleVO> findListByUserId(@Param("id") String id);
}
