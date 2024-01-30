package com.example.log_and_proof_back.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T>Result<T> success(){
        return new Result<>(1,"success",null);
    }

    public static <T>Result<T> success(Integer code,String msg,T data){
        return new Result<>(code,msg,data);
    }

    public static <T>Result<T> error(){
        return new Result<>(0,"error",null);
    }

    public static <T>Result<T> error(String msg){return new Result<>(0, msg, null);}

    public static <T>Result<T> error(Integer code,String msg,T data){
        return new Result<>(code,msg,data);
    }
}
