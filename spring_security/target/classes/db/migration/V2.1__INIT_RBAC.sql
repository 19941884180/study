CREATE TABLE user (
  id varchar(36) NOT NULL,
  user_name varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE role (
  id varchar(36) NOT NULL,
  role_name varchar(100) NOT NULL,
  role_desc varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE perm (
  id varchar(36) NOT NULL,
  perm_name varchar(100) NOT NULL,
  perm_desc varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_role (
  id varchar(36) NOT NULL,
  user_id varchar(36) NOT NULL,
  role_id varchar(36) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE role_perm (
  id varchar(36) NOT NULL,
  user_id varchar(36) NOT NULL,
  perm_id varchar(36) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;