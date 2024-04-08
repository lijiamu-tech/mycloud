package com.beginner.device.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beginner.common.util.utils.Result;
import com.beginner.device.entity.DeviceLog;
import com.beginner.device.service.DeviceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceLogController {

    @Autowired
    private DeviceLogService deviceLogService;

    @GetMapping("/log/page")
    public Result<Page<DeviceLog>> page(DeviceLog deviceLog,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        long countSize = deviceLogService.count(Wrappers.query(deviceLog));
        Page<DeviceLog> pageResult = deviceLogService.page(Page.of(pageNum, pageSize), Wrappers.query(deviceLog));
        pageResult.setTotal(countSize);
        return Result.ok(pageResult);
    }

    @GetMapping("/log/list")
    public Result<List<DeviceLog>> list(DeviceLog deviceLog){
        return Result.ok(deviceLogService.list(Wrappers.query(deviceLog)));
    }

    @PostMapping("/log/add")
    public Result<Boolean> add(@RequestBody DeviceLog deviceLog){
        return Result.ok(deviceLogService.save(deviceLog));
    }

    @PutMapping("/log/edit")
    public Result<Boolean> edit(@RequestBody DeviceLog deviceLog){
        return Result.ok(deviceLogService.updateById(deviceLog));
    }

    @DeleteMapping("/log/delete/{ids}")
    public Result<Boolean> delete(@PathVariable("ids") List<Long> ids){
        return Result.ok(deviceLogService.removeByIds(ids));
    }
}
