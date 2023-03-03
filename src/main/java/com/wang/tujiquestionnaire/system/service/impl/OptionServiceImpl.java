package com.wang.tujiquestionnaire.system.service.impl;

import com.wang.tujiquestionnaire.system.entity.Option;
import com.wang.tujiquestionnaire.system.mapper.OptionMapper;
import com.wang.tujiquestionnaire.system.service.IOptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 调查问卷问题选项主表 服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements IOptionService {

}
