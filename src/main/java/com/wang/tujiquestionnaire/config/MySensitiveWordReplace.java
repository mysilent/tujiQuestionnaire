package com.wang.tujiquestionnaire.config;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplace;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplaceContext;

/**
 * ClassName: MySensitiveWordReplace
 * Package: IntelliJ IDEA
 * Description: 敏感字过滤自定义
 *
 * @Author: wang
 * Create:2023/4/29-18:21
 */
public class MySensitiveWordReplace implements ISensitiveWordReplace {
    @Override
    public String replace(ISensitiveWordReplaceContext context) {
        String sensitiveWord = context.sensitiveWord();
        // 自定义不同的敏感词替换策略，可以从数据库等地方读取
        if ("gender".equals(sensitiveWord)) {
            return "gender";
        }
        if ("64".equals(sensitiveWord)) {
            return "64";
        }if ("89".equals(sensitiveWord)) {
            return "89";
        }if ("644".equals(sensitiveWord)) {
            return "644";
        }if ("678".equals(sensitiveWord)) {
            return "678";
        }if ("123".equals(sensitiveWord)) {
            return "123";
        }if ("234".equals(sensitiveWord)) {
            return "234";
        }if ("456".equals(sensitiveWord)) {
            return "456";
        }if ("567".equals(sensitiveWord)) {
            return "567";
        }if ("789".equals(sensitiveWord)) {
            return "789";
        }
        // 其他默认使用 * 代替
        int wordLength = context.wordLength();
        return CharUtil.repeat('*', wordLength);
    }

}