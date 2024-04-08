package com.beginner.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beginner.modules.system.entity.SysAuth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysAuthMapper extends BaseMapper<SysAuth> {
}
