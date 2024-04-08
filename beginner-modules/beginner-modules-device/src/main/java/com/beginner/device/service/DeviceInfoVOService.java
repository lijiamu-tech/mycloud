package com.beginner.device.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beginner.device.entity.DeviceInfo;
import com.beginner.device.entity.DeviceStatus;
import com.beginner.device.entity.vo.DeviceInfoVO;
import com.beginner.device.mapper.DeviceInfoVOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DeviceInfoVOService extends ServiceImpl<DeviceInfoVOMapper, DeviceInfoVO> {

    @Autowired
    private DeviceInfoVOMapper deviceInfoVOMapper;

    public Page<DeviceInfoVO> getDeviceInfoVOPage(Page page, DeviceInfoVO deviceInfoVO) {
        return deviceInfoVOMapper.selectDeviceInfoVOPage(page, deviceInfoVO);
    }

    public long selectDeviceInfoVOCount(DeviceInfoVO deviceInfoVO){
        return deviceInfoVOMapper.selectDeviceInfoVOCount(deviceInfoVO);
    }

    public List<DeviceInfoVO> selectDeviceInfoVOList(DeviceInfoVO deviceInfoVO){
        return deviceInfoVOMapper.selectDeviceInfoVOList(deviceInfoVO);
    }

    @Transactional(rollbackFor = Exception.class)   //将两个insert合成一个事务，报错回滚
    public Boolean insertDeviceInfoVO(DeviceInfoVO deviceInfoVO){
        // 创建初始返回值
        Boolean result = true;
        // 创建deviceInfo添加对象
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceName(deviceInfoVO.getDeviceName());
        deviceInfo.setDeviceModel(deviceInfoVO.getDeviceModel());
        deviceInfo.setManufacturer(deviceInfoVO.getManufacturer());
        deviceInfo.setLocation(deviceInfoVO.getLocation());
        // 判断在deviceInfo表里是否存在记录
        List<DeviceInfoVO> deviceInfoVOList = deviceInfoVOMapper.selectDeviceInfoVOList(deviceInfoVO);
        if (deviceInfoVOList.isEmpty()) {
            result = deviceInfoVOMapper.insertDeviceInfo(deviceInfo);
        }
        // 从deviceInfo中取deviceId给deviceStatus添加对象
        DeviceInfo deviceInfoVOAfterList = deviceInfoVOMapper.selectDeviceInfoList(deviceInfo);
        Integer deviceId = deviceInfoVOAfterList.getDeviceId();
        System.out.println(deviceId);
        DeviceStatus deviceStatus = new DeviceStatus();
        deviceStatus.setDeviceId(deviceId);

        deviceStatus.setCurrentStatus(deviceInfoVO.getCurrentStatus());
        deviceStatus.setStatusTime(deviceInfoVO.getStatusTime());
        if (deviceInfoVO.getStatusTime() == null){
            deviceStatus.setStatusTime(new Date());
        }

        result = result && deviceInfoVOMapper.insertDeviceStatus(deviceStatus);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)   //将两个update合成一个事务，报错回滚
    public Boolean updateDeviceInfoVO(DeviceInfoVO deviceInfoVO){
        // 创建deviceInfo修改对象
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(deviceInfoVO.getDeviceId());
        deviceInfo.setDeviceName(deviceInfoVO.getDeviceName());
        deviceInfo.setDeviceModel(deviceInfoVO.getDeviceModel());
        deviceInfo.setManufacturer(deviceInfoVO.getManufacturer());
        deviceInfo.setLocation(deviceInfoVO.getLocation());
        // 创建deviceStatus修改对象
        DeviceStatus deviceStatus = new DeviceStatus();
        deviceStatus.setStatusId(deviceInfoVO.getStatusId());
        deviceStatus.setDeviceId(deviceInfoVO.getDeviceId());
        deviceStatus.setCurrentStatus(deviceInfoVO.getCurrentStatus());

        // 执行设备信息和设备状态的更新，并返回结果
        Boolean deviceInfoUpdateResult = deviceInfoVOMapper.updateDeviceInfo(deviceInfo);
        Boolean deviceStatusUpdateResult = deviceInfoVOMapper.updateDeviceStatus(deviceStatus);

        // 如果其中一个更新失败，则回滚事务
        if (!deviceInfoUpdateResult || !deviceStatusUpdateResult) {
            throw new RuntimeException("Failed to update device information or device status. Rolling back transaction.");
        }

        // 如果都成功，则返回 true
        return true;
    }

    @Transactional(rollbackFor = Exception.class)   //将多个delete合成一个事务，报错回滚
    public Boolean deleteDeviceInfoVO(List<Integer> ids) {

        Boolean result = true;
        // 判断是否为空
        if (ids.isEmpty()) {
            return false;

            // 判断ids的长度是否为1，是否需要循环
        }else if (ids.size() == 1) {
            if (deviceInfoVOMapper.selectDeviceStatusCount(ids.get(0)) > 0) {
                result = deviceInfoVOMapper.deleteDeviceStatus(ids.get(0));
            }else {
                result = deviceInfoVOMapper.deleteDeviceInfo(ids.get(0));
            }
        }else {

            // 利用循环依次删除
            for (int i = 0; i < ids.size(); i++) {
                if (deviceInfoVOMapper.selectDeviceStatusCount(ids.get(i)) > 0) {
                    result = result && deviceInfoVOMapper.deleteDeviceStatus(ids.get(i));
                }else {
                    result = result && deviceInfoVOMapper.deleteDeviceInfo(ids.get(i));
                }
            }
        }
        if (!result) {
            throw new RuntimeException("Failed to delete device information or device status. Rolling back transaction.");
        }

        return true;
    }


}
