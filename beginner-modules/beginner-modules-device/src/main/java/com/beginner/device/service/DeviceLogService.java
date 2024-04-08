package com.beginner.device.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beginner.device.entity.DeviceLog;
import com.beginner.device.mapper.DeviceLogMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceLogService extends ServiceImpl<DeviceLogMapper, DeviceLog> {
}
