package com.wang.tujiquestionnaire.background.service.impl;

import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.wang.tujiquestionnaire.background.mapper.BackgroundUserMapper;
import com.wang.tujiquestionnaire.background.service.IBackgroundUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.tujiquestionnaire.system.entity.dto.UserAndUserDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-04-25
 */
@Service
public class BackgroundUserServiceImpl extends ServiceImpl<BackgroundUserMapper, BackgroundUser> implements IBackgroundUserService {
@Autowired
BackgroundUserMapper backgroundUserMapper;
    @Override
    public Map<String, Object> findpage(Integer pageNum,Integer pageSize,String name,String username) {
        pageNum = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        Integer total = backgroundUserMapper.selectTotal(name,username);
        List<UserAndUserDetailDto> data = backgroundUserMapper.findPage(pageNum, pageSize,name,username);
        map.put("data", data);
        map.put("total", total);
        return map;
    }
}
