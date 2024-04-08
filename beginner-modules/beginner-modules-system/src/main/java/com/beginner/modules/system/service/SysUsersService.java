package com.beginner.modules.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beginner.modules.system.entity.SysUsers;
import com.beginner.modules.system.mapper.SysUsersMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUsersService extends ServiceImpl<SysUsersMapper, SysUsers> {
}
