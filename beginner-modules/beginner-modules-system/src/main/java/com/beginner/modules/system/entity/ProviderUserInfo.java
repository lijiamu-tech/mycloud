package com.beginner.modules.system.entity;

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
 * (ProviderUserInfo)实体类
 *
 * @author ljm
 * @since 2024-03-28 17:30:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("provider_user_info")
public class ProviderUserInfo implements Serializable {
    private static final long serialVersionUID = 712776114860018426L;
/**
     * 第三方用户ID
     */
    @TableId
    private Integer userId;
/**
     * 第三方服务名称
     */
    @TableField("provider_name")
    private String providerName;
/**
     * 第三方用户用户名
     */
    @TableField("username")
    private String username;
/**
     * 第三方用户头像URL
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;
/**
     * 第三方用户邮箱
     */
    @TableField("email")
    private String email;
/**
     * 第三方用户性别
     */
    @TableField("gender")
    private String gender;
/**
     * 第三方用户生日
     */
    @TableField("birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

}

