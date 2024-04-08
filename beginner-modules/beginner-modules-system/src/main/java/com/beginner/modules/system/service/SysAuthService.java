package com.beginner.modules.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beginner.modules.system.entity.SysAuth;
import com.beginner.modules.system.mapper.SysAuthMapper;
import org.springframework.stereotype.Service;

@Service
public class SysAuthService extends ServiceImpl<SysAuthMapper, SysAuth>{
}
