package com.qxwz.qle.controller;

import com.qxwz.qle.constant.ResponseCode;
import com.qxwz.qle.entity.QLEResponse;
import com.qxwz.qle.entity.vo.PreEditVo;
import com.qxwz.qle.entity.vo.PreOnlineOrOfflineVo;
import com.qxwz.qle.exception.QleBizException;
import com.qxwz.qle.service.IExpressService;
import com.qxwz.qle.util.ExpressRunner;
import com.ql.util.express.DefaultContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/wire")
@DependsOn("springBeanFactoryForQL")
public class QLEController {

    @Autowired
    IExpressService expressService;

    @RequestMapping("/execute")
    public Object test(@RequestBody Map<String, Object> map,
                       HttpServletRequest request) throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("request",request);
        context.put("originBody", map);
        log.info("中间层入参："+map.toString());
        if ( ((Map) map.get("param")) == null
                || ((Map) map.get("param")).get("qleProject") == null
                || (((Map) map.get("param")).get("qlePath")) == null ){
            throw new QleBizException(ResponseCode.INCORRECT_PARAM_FAIL.getCode(), ResponseCode.INCORRECT_PARAM_FAIL.getMessage());
        }
        String qleProject = (String) (((Map) map.get("param")).get("qleProject"));
        String qlePath = (String) (((Map) map.get("param")).get("qlePath"));
        String express = expressService.QueryExpressByProjectAndPath(qleProject, qlePath);
        Object result = ExpressRunner.runner.execute(express, context, null, true, false);
        return result;
    }

}
