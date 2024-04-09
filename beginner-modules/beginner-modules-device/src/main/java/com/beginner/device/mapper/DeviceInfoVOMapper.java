package com.beginner.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beginner.device.entity.DeviceInfo;
import com.beginner.device.entity.DeviceStatus;
import com.beginner.device.entity.vo.DeviceInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface DeviceInfoVOMapper extends BaseMapper<DeviceInfoVO> {

    Page<DeviceInfoVO> selectDeviceInfoVOPage(Page page, @Param("deviceInfoVO") DeviceInfoVO deviceInfoVO);
    
    List<DeviceInfoVO> selectDeviceInfoVOList(@Param("deviceInfoVO") DeviceInfoVO deviceInfoVO);

    DeviceInfo selectDeviceInfoList(@Param("deviceInfo") DeviceInfo deviceInfo);

    DeviceStatus selectDeviceStatusList(@Param("deviceStatus") DeviceStatus deviceStatus);

    long selectDeviceInfoVOCount(@Param("deviceInfoVO") DeviceInfoVO deviceInfoVO);
    long selectDeviceStatusCount(Integer id);

    Boolean insertDeviceInfo(@Param("DeviceInfo") DeviceInfo deviceInfo);
    Boolean insertDeviceStatus(@Param("DeviceStatus") DeviceStatus deviceStatus);

    Boolean updateDeviceInfo(@Param("DeviceInfo") DeviceInfo deviceInfo);
    Boolean updateDeviceStatus(@Param("DeviceStatus") DeviceStatus deviceStatus);

    Boolean deleteDeviceInfo(Integer id);
    Boolean deleteDeviceStatus(Integer id);

}
