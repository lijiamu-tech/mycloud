package com.beginner.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * (DeviceInfo)表实体类
 *
 * @author ljm
 * @since 2024-04-07 11:14:49
 */
@TableName("device_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfo implements Serializable {
//设备ID
    @TableId
    private Integer deviceId;
//设备名称
    @TableField("device_name")
    private String deviceName;
//设备型号
    @TableField("device_model")
    private String deviceModel;
//生产厂商
    @TableField("manufacturer")
    private String manufacturer;
//安装位置
    @TableField("location")
    private String location;

}

