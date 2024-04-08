package com.beginner.device.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * (DeviceLog)表实体类
 *
 * @author ljm
 * @since 2024-04-07 11:15:08
 */
@TableName("device_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceLog implements Serializable {
//日志ID
    @TableId
    private Integer logId;
//设备ID
    @TableField("device_id")
    private Integer deviceId;
//日志时间
    @TableField("log_time")
    private Date logTime;
//日志类型
    @TableField("log_type")
    private String logType;
//日志信息
    @TableField("log_content")
    private String logContent;

}

