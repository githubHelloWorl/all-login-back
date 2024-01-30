package com.example.log_and_proof_back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.log_and_proof_back.model.dao.StudentMapper;
import com.example.log_and_proof_back.model.entity.Student;
import com.example.log_and_proof_back.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public String findPassByNo(@Param("sno") String sno){
        return studentMapper.findPassByNo(sno);
    }
}
