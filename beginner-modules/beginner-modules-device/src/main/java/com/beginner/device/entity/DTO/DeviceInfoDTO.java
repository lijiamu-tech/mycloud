package com.beginner.device.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * deviceinfo data transfer object(数据传输对象)
 *@author ljm
 *date:2024.04.07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfoDTO {

    private String deviceId;

    private String deviceName;

    private String deviceModel;

    private String location;

    private String manufacturer;

    public static class DeviceStatus{
        //状态ID
        private Integer statusId;
        //设备ID
        private Integer deviceId;
        //状态时间
        private Date statusTime;
        //设备状态
        private String currentStatus;
    }
}
