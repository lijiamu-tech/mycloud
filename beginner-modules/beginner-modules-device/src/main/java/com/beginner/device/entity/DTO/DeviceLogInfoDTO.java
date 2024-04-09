package com.beginner.device.entity.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 * deviceLog data transfer object(数据传输对象)
 *@author ljm
 *date:2024.04.07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceLogInfoDTO {
    //日志ID
    private Integer logId;
    //设备ID
    private Integer deviceId;
    //日志时间
    private Date logTime;
    //日志类型
    private String logType;
    //日志信息
    private String logContent;
    public static class DeviceInfo {
        private String deviceId;

        private String deviceName;

        private String deviceModel;

        private String location;

        private String manufacturer;
    }
}
