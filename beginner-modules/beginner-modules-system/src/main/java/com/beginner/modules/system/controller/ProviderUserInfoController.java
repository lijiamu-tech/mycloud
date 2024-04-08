package com.beginner.modules.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beginner.common.util.utils.Result;
import com.beginner.modules.system.entity.ProviderUserInfo;
import com.beginner.modules.system.entity.SysUsers;
import com.beginner.modules.system.service.ProviderUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * providerUserInfo
 *@author ljm
 *date:2024.03.30
 */
@RestController
@RequestMapping("/provider")
public class ProviderUserInfoController {

    // 自动注入ProviderUserInfoService
     @Autowired
     private ProviderUserInfoService baseService;

     // 查询分页数据
     @GetMapping("/query/page")
     public Result<Page<ProviderUserInfo>> queryPage(ProviderUserInfo providerUserInfo,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int pageSize) {

         // 调用service查询数据
         Page<ProviderUserInfo> pageResult = baseService.page(Page.of(page, pageSize), Wrappers.query(providerUserInfo));
         return Result.ok(pageResult);
     }


     // 查询列表数据
     @GetMapping("/query")
     public Result<List<ProviderUserInfo>> query(ProviderUserInfo providerUserInfo) {
         return Result.ok(baseService.list(Wrappers.query(providerUserInfo)));
     }


     // 添加数据
     @PostMapping("/add")
     public Result<Boolean> add(@RequestBody ProviderUserInfo providerUserInfo) {
         return Result.ok(baseService.save(providerUserInfo));
     }

     // 编辑数据
     @PutMapping("/edit")
     public Result<Boolean> edit(@RequestBody ProviderUserInfo providerUserInfo) {
         return Result.ok(baseService.updateById(providerUserInfo));
     }


     // 删除数据
     @DeleteMapping("/delete/{id}")
     public Result<Boolean> delete(@PathVariable("id") Long id) {
         return Result.ok(baseService.removeById(id));
     }
}
