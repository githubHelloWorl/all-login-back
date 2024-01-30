package com.example.log_and_proof_back.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.log_and_proof_back.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select spass from student where sno = #{sno}")
    public String findPassByNo(@Param("sno") String sno);
}
