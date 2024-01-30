package com.example.log_and_proof_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.log_and_proof_back.model.entity.Student;

public interface StudentService extends IService<Student> {
    public String findPassByNo(String sno);
}
