package com.wang.tujiquestionnaire.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Result
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/3/1-11:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(200,"成功",null);
    }
    public static Result success(Object date){
        return new Result(200,"成功",date);
    }
    public static Result success(String msg,Object data){
        return new Result(200,msg,data);
    }
    public static Result error(String msg){
        return new Result(500,msg,null);
    }
    public static Result error(){
        return new Result(500,"系统出错,请联系管理员",null);
    }
    public static Result error(Integer code,String msg){
        return new Result(code,msg,null);
    }
}
