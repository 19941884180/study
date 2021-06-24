package com.mdy.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role")
public class RoleVO {
    @Id
    private String id;
    private String roleName;
    private String roleDesc;
}
