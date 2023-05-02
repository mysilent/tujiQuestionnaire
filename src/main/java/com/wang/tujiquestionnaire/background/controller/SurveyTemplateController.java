package com.wang.tujiquestionnaire.background.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.background.entity.SurveyTemplate;
import com.wang.tujiquestionnaire.background.service.impl.SurveyTemplateServiceImpl;
import com.wang.tujiquestionnaire.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-04-26
 */
@Api
@RestController
@RequestMapping("/background/surveyTemplate")
public class SurveyTemplateController {
    @Autowired
    SurveyTemplateServiceImpl surveyTemplateService;

    @ApiOperation("问卷模板查询")
    @GetMapping("/selectTemplate")
    public Map<String,Object>selectTemplate(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum,@RequestParam("surveyName")String surveyName,@RequestParam("surveyId") String surveyId){
        return surveyTemplateService.selectTemplate(pageSize,pageNum,"1",surveyName,surveyId);
    }

    @ApiOperation("模板申请")
    @PostMapping("/templateApplication")
    public Result templateApplication(@RequestBody SurveyTemplate surveyTemplate) throws UnsupportedEncodingException {
        boolean save = true;
        // 前端进行了url编码，还需要将数据转回序列化数据
        surveyTemplate.setContent(URLDecoder.decode((surveyTemplate.getContent()), "UTF-8"));
        if (surveyTemplateService.getById(surveyTemplate.getId()) == null) {
            surveyTemplate.setState("0");
            save = surveyTemplateService.save(surveyTemplate);
        } else {
            return Result.error(1000, "已申请模板，不能重复申请~");
        }
        return save ? Result.success() : Result.error();
    }

    @ApiOperation("模板申请查询")
    @GetMapping("/selectTemplateApplication")
    public Map<String, Object> selectTemplateApplicationNo(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum) {
        return surveyTemplateService.templateApplicationpage(pageSize,pageNum,"0");

    }

    @ApiOperation("模板申请通过")
    @GetMapping ("/templateApplicationYes")
    public Result templateApplicationNoYes(@RequestParam("id")String id){
        return surveyTemplateService.templateApplicationYes(id);
    }
    @ApiOperation("模板申请不通过")
    @GetMapping("/templateApplicationNo")
    public  Result templateApplicationNoNo(@RequestParam("id")String id){
        return surveyTemplateService.templateApplicationNo(id);
    }
    @ApiOperation("问卷模板查看")
    @GetMapping("/selectTemplatePreview")
    public SurveyTemplate selectTemplatePreview(@RequestParam("id") String id){
        return surveyTemplateService.getById(id);
    }

}
