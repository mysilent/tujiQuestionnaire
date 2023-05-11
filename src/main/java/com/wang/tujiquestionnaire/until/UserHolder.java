package com.wang.tujiquestionnaire.until;


import com.wang.tujiquestionnaire.system.entity.dto.UserDto;
/**
 * ClassName: Constant
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/5-11:37
 */
public class UserHolder {
    private static final ThreadLocal<UserDto> tl = new ThreadLocal<>();

    public static void saveUser(UserDto user){
        tl.set(user);
    }

    public static UserDto getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
