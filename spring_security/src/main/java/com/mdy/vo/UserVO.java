package com.mdy.vo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user")
@Entity
public class UserVO {
    @Id
    private String userName;
    private String password;
}
