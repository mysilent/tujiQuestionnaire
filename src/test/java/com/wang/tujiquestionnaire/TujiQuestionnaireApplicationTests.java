package com.wang.tujiquestionnaire;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.wang.tujiquestionnaire.system.entity.User;
import com.wang.tujiquestionnaire.system.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TujiQuestionnaireApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void page() {
        Page<User> userPage = new Page<>(1, 5);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Page<User> userPage1 = userMapper.selectPage(userPage, userQueryWrapper);
        System.out.println(userPage1);
        System.out.println("----------------------");
        System.out.println(userPage1.getRecords());
        System.out.println(userPage1.getRecords().size());
    }

    @Test
    void testWord() {
        String text = "邮箱";
//        System.out.println(SensitiveWordHelper.contains(text));
        //默认将敏感词转换为**
        System.out.println(SensitiveWordHelper.replace(text));
        //配置将敏感词转换为00
//        System.out.println(SensitiveWordHelper.replace(text, '0'));
//        System.out.println(SensitiveWordHelper.findFirst(text));
//        System.out.println(SensitiveWordHelper.findFirst(text, WordResultHandlers.word()));
//        System.out.println(SensitiveWordHelper.findFirst(text, WordResultHandlers.raw()));
//        System.out.println(SensitiveWordHelper.findAll(text));
//        System.out.println(SensitiveWordHelper.findAll(text, WordResultHandlers.word()));
//        System.out.println(SensitiveWordHelper.findAll(text, WordResultHandlers.raw()));
    }

}
