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
 * (DeviceStatus)表实体类
 *
 * @author ljm
 * @since 2024-04-07 11:15:40
 */
@TableName("device_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStatus implements Serializable {
//状态ID
    @TableId
    private Integer statusId;
//设备ID
    @TableField("device_id")
    private Integer deviceId;
//状态时间
    @TableField("status_time")
    private Date statusTime;
//设备状态
    @TableField("current_status")
    private String currentStatus;

}

