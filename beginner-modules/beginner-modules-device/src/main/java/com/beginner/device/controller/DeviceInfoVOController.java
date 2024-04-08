package com.beginner.device.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beginner.common.util.utils.Result;
import com.beginner.device.entity.vo.DeviceInfoVO;
import com.beginner.device.service.DeviceInfoVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceInfoVOController {

    @Autowired
    private DeviceInfoVOService deviceInfoVOService;


    // @ApiOperation(value = "分页查询设备信息", notes = "分页查询设备信息")
    @GetMapping("/query/page")
    // 返回分页查询结果
    public Result<Page<DeviceInfoVO>> page(DeviceInfoVO deviceInfoVo,
                                           // 当前页码
                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                           // 每页显示数据条数
                                           @RequestParam(defaultValue = "10") Integer pageSize){
        // 查询设备信息表中满足条件的数量
        long countSize = deviceInfoVOService.selectDeviceInfoVOCount(deviceInfoVo);
        // 查询设备信息表中满足条件的数据分页
        Page<DeviceInfoVO> pageResult = deviceInfoVOService.getDeviceInfoVOPage(new Page<>(pageNum, pageSize),deviceInfoVo);
        // 设置查询到的数据的总数量
        pageResult.setTotal(countSize);
        // 返回查询到的数据
        return Result.ok(pageResult);
    }

    // @ApiOperation(value = "查询设备信息", notes = "查询设备信息")
    @GetMapping("/query")
    // 返回查询结果
    public Result<List<DeviceInfoVO>> query(DeviceInfoVO deviceInfoVo) {
        // 查询设备信息表中满足条件的数据
        return Result.ok(deviceInfoVOService.selectDeviceInfoVOList(deviceInfoVo));
    }

    // @ApiOperation(value = "添加设备信息", notes = "添加设备信息")
    @PostMapping("/add")
    // 返回添加结果
    public Result<Boolean> add(@RequestBody DeviceInfoVO deviceInfoVO){
        // 添加一条数据到设备信息表
        return Result.ok(deviceInfoVOService.insertDeviceInfoVO(deviceInfoVO));
    }

    // @ApiOperation(value = "编辑设备信息", notes = "编辑设备信息")
    @PutMapping("/edit")
    // 返回编辑结果
    public Result<Boolean> edit(@RequestBody DeviceInfoVO deviceInfoVO){
        // 编辑设备信息表中的数据
        return Result.ok(deviceInfoVOService.updateDeviceInfoVO(deviceInfoVO));
    }

    // @ApiOperation(value = "删除设备信息", notes = "删除设备信息")
    @DeleteMapping("/delete/{ids}")
    // 返回删除结果
    public Result<Boolean> delete(@PathVariable("ids") Integer[] ids){
        // 根据ids删除设备信息表中的数据
        return Result.ok(deviceInfoVOService.deleteDeviceInfoVO(Arrays.asList(ids)));
    }

}
