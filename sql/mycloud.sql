create database if not EXISTS mycloud;

use mycloud;

create table sys_users(
	user_id INT NOT NULL auto_increment COMMENT '用户ID',
  user_name VARCHAR(50) UNIQUE NOT NULL COMMENT '用户账户',
  user_pwd VARCHAR(100) NOT NULL COMMENT '用户密码',
  email VARCHAR(100) COMMENT '邮箱',
  phone VARCHAR(20) NOT NULL COMMENT '手机号',
  created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY(user_id)
);

create table sys_auth(
	authorization_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '授权ID',
	user_id INT NOT NULL COMMENT '用户ID',
	provider_name VARCHAR(50) NOT NULL COMMENT '第三方服务名称',
	access_token VARCHAR(255) NOT NULL COMMENT '访问令牌',
	token_type VARCHAR(50) COMMENT '令牌类型',
	expires_at TIMESTAMP COMMENT '过期时间',
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间',
	FOREIGN KEY (user_id) REFERENCES sys_users(user_id)
);

CREATE TABLE provider_user_info (
    user_id INT NOT NULL	COMMENT '第三方用户ID',
    provider_name VARCHAR(50) NOT NULL COMMENT '第三方服务名称',
    username VARCHAR(100) COMMENT '第三方用户用户名',
    avatar_url VARCHAR(255) COMMENT '第三方用户头像URL',
    email VARCHAR(100) COMMENT '第三方用户邮箱',
    gender VARCHAR(20) COMMENT '第三方用户性别',
    birthday DATE COMMENT '第三方用户生日',
    
    PRIMARY KEY (user_id, provider_name), -- 使用联合主键来确保唯一性
    FOREIGN KEY (user_id) REFERENCES sys_users(user_id) -- 外键关联到用户表
);


-- 插入用户表数据：
INSERT INTO sys_users (user_name, user_pwd, email, phone)
VALUES ('user1', 'password1', 'user1@example.com', '1234567890'),
       ('user2', 'password2', 'user2@example.com', '9876543210');

-- 插入第三方授权表数据：
INSERT INTO sys_auth (user_id, provider_name, access_token, token_type, expires_at)
VALUES (1, 'google', 'google_access_token_1', 'Bearer', '2024-04-01 00:00:00'),
       (2, 'facebook', 'facebook_access_token_1', 'Bearer', '2024-04-01 00:00:00');

-- 插入第三方用户信息表数据：
INSERT INTO provider_user_info (user_id, provider_name, username, avatar_url, email, gender, birthday)
VALUES (1, 'google', 'user1_google', 'https://google-avatar-url.com/user1', 'user1@google.com', 'male', '1990-01-01'),
       (2, 'facebook', 'user2_facebook', 'https://facebook-avatar-url.com/user2', 'user2@facebook.com', 'female', '1995-05-05');
