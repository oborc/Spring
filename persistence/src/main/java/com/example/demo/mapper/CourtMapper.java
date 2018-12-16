package com.example.demo.mapper;

import com.example.demo.domain.Court;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CourtMapper extends MySqlMapper<Court>,BaseMapper<Court> {
}
