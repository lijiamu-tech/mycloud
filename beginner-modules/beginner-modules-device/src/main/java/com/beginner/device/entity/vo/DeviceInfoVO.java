package com.beginner.device.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 *  设备信息view object
 *@author ljm
 *date:2024.04.07
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceInfoVO implements Serializable{

    private Integer deviceId;

    private String deviceName;

    private String deviceModel;

    private String currentStatus;

    private String location;

    private String manufacturer;

    private Date statusTime;

    private Integer statusId;
    // 省略getter和setter方法
}
