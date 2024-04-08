package com.beginner.modules.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beginner.common.util.utils.Result;
import com.beginner.common.util.utils.TokenGenerator;
import com.beginner.modules.system.entity.SysUsers;
import com.beginner.modules.system.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
/**
 * SysUsers
 *@author ljm
 *date:2024.03.30
 */
@RestController
@RequestMapping("/sysUsers")
public class SysUsersController {

    @Autowired
    private SysUsersService baseService;


    /**
     * 分页查询用户信息
     * @param sysUsers 用户查询条件
     * @param page 当前页
     * @param pageSize 每页显示数量
     * @return 分页查询结果
     */
    @GetMapping("/query/page")
    public Result<Page<SysUsers>> queryPage(SysUsers sysUsers,
                                            @RequestParam(value = "page",defaultValue = "1") int page,
                                            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        long count = baseService.count(Wrappers.query(sysUsers));
        // 构建分页对象
        Page<SysUsers> pageParam = new Page<>(page, pageSize);
        // 调用service查询数据
        Page<SysUsers> pageResult = baseService.page(pageParam, Wrappers.query(sysUsers));
        pageResult.setTotal(count);
        return Result.ok(pageResult);
    }

    /**
     * 查询用户信息
     * @param sysUsers 用户查询条件
     * @return 查询结果
     */
    @GetMapping("/query")
    public Result<List<SysUsers>> query(SysUsers sysUsers) {
        System.out.println(baseService.list(Wrappers.query(sysUsers)));
        return Result.ok(baseService.list(Wrappers.query(sysUsers)));
    }

    @GetMapping("/token")
    public Result<String> queryToken() {
        return Result.ok(TokenGenerator.generateToken());
    }

    /**
     * 添加用户信息
     * @param sysUsers 用户信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SysUsers sysUsers) {
        return Result.ok(baseService.save(sysUsers));
    }

    /**
     * 编辑用户信息
     * @param sysUsers 用户信息
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public Result<Boolean> edit(@RequestBody SysUsers sysUsers) {
        return Result.ok(baseService.updateById(sysUsers));
    }

    /**
     * 批量删除用户信息
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long[] id) {
        return Result.ok(baseService.removeBatchByIds(Arrays.asList(id)));
    }
}