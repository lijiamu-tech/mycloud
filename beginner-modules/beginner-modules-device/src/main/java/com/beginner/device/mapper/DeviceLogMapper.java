package com.beginner.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beginner.device.entity.DeviceLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceLogMapper extends BaseMapper<DeviceLog> {
}
