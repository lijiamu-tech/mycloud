package com.beginner.modules.system.entity;

import com.alibaba.nacos.shaded.org.checkerframework.checker.formatter.qual.Format;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysUsers)实体类
 *
 * @author ljm
 * @since 2024-03-28 17:30:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_users")
public class SysUsers implements Serializable {
    private static final long serialVersionUID = 145578991532936502L;
/**
     * 用户ID
     */
    @TableId
    private Integer userId;
/**
     * 用户账户
     */
    @TableField(value = "user_name")
    private String userName;
/**
     * 用户密码
     */
    @TableField(value = "user_pwd")
    private String userPwd;
/**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;
/**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;
/**
     * 创建时间
     */
    @TableField(value = "created_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
/**
     * 修改时间
     */

    @TableField(value = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
}

