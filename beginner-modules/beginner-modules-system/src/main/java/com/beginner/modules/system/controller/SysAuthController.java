package com.beginner.modules.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beginner.common.util.utils.Result;
import com.beginner.modules.system.entity.SysAuth;
import com.beginner.modules.system.entity.SysUsers;
import com.beginner.modules.system.service.SysAuthService;
import com.beginner.modules.system.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * SysAuth
 *@author ljm
 *date:2024.03.30
 */
@RestController
@RequestMapping("/sysAuth")
public class SysAuthController {

    /**
 * 注入SysAuthService
 */
@Autowired
    private SysAuthService baseService;


    /**
     * 查询单条数据
     * @param sysAuth 查询条件
     * @return 返回查询结果
     */
    @GetMapping("/query/page")
    public Result<Page<SysAuth>> queryPage(SysAuth sysAuth,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int pageSize) {

        // 调用service查询数据
        Page<SysAuth> pageResult = baseService.page(Page.of(page, pageSize), Wrappers.query(sysAuth));
        return Result.ok(pageResult);
    }


    /**
     * 查询多条数据
     * @param sysAuth 查询条件
     * @return 返回查询结果
     */
    @GetMapping("/query")
    public Result<List<SysAuth>> query(SysAuth sysAuth) {
        System.out.println(baseService.list(Wrappers.query(sysAuth)));
        return Result.ok(baseService.list(Wrappers.query(sysAuth)));
    }


    /**
     * 添加数据
     * @param sysAuth 添加的数据
     * @return 返回添加结果
     */
   @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SysAuth sysAuth) {
        return Result.ok(baseService.save(sysAuth));
    }


    /**
     * 修改数据
     * @param sysAuth 修改的数据
     * @return 返回修改结果
     */
    @PutMapping("/edit")
    public Result<Boolean> edit(@RequestBody SysAuth sysAuth) {
        return Result.ok(baseService.updateById(sysAuth));
    }


    /**
     * 删除数据
     * @param id 删除的数据id
     * @return 返回删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return Result.ok(baseService.removeById(id));
    }
}
