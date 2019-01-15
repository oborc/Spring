package com.example.demo.mapper;

import com.example.demo.domain.originUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface originUserMapper extends Mapper<originUser>, MySqlMapper<originUser> {

    @Insert("insert ignore into test_unique (first_name,last_name) values (#{firstName},#{lastName})")
    void insertUnique(@Param("firstName") String firstName,@Param("lastName") String lastName);
}
