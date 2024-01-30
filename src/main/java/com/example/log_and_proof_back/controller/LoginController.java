package com.example.log_and_proof_back.controller;

import com.example.log_and_proof_back.model.pojo.Result;
import com.example.log_and_proof_back.model.entity.Student;
import com.example.log_and_proof_back.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.log_and_proof_back.utils.JwtUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/st")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        log.info("学生登录: {}",student);

        if(student.getSpass() == null||student.getSpass().equals("")){
            return Result.error("密码不能为空");
        }

        if(student.getSno() == null||student.getSno().equals("")){
            return Result.error("账号不能为空");
        }

        if((studentService.findPassByNo(student.getSno())).equals(student.getSpass())){
            Map<String,Object> claims = new HashMap<>();

            // 随意定义数据
            Date date = new Date();
            long t = date.getTime();
            String s = Long.toString(t).substring(5);
            claims.put("id",s);

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(1,"success",jwt);
        }

        return Result.error("密码或账号不正确");
    }

    @GetMapping("/test1")
    public Result test1(){
        return Result.success(1,"aaa","data_aaa");
    }

    @GetMapping("/test2")
    public Result test2(@RequestParam("name") String name) {return Result.success(1,"test2","data_aaa");}
}



