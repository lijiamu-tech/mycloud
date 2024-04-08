package com.beginner.modules.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beginner.modules.system.entity.ProviderUserInfo;
import com.beginner.modules.system.mapper.ProviderUserInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class ProviderUserInfoService extends ServiceImpl<ProviderUserInfoMapper, ProviderUserInfo> {
}
