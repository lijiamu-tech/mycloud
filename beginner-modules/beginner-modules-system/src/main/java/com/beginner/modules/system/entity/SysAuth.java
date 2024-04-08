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
 * (SysAuth)实体类
 *
 * @author ljm
 * @since 2024-03-28 17:30:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_auth")
public class SysAuth implements Serializable {
    private static final long serialVersionUID = 821134948731851310L;
/**
     * 授权ID
     */
    @TableId
    private Integer authorizationId;
/**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;
/**
     * 第三方服务名称
     */
    @TableField("provider_name")
    private String providerName;
/**
     * 访问令牌
     */
    @TableField("access_token")
    private String accessToken;
/**
     * 令牌类型
     */
    @TableField("token_type")
    private String tokenType;
/**
     * 过期时间
     */
    @TableField("expires_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiresAt;
/**
     * 创建时间
     */
    @TableField("created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;


}

