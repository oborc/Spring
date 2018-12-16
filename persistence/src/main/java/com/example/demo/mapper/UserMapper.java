package com.example.demo.mapper;

import com.example.demo.domain.User;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;
public interface UserMapper extends BaseMapper<User>,MySqlMapper<User>{
}
