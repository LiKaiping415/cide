package com.qxwz.qle.controller;

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
public class QLEController {

    @RequestMapping("/execute")
    public String test() throws Exception {
        return "ok!";
    }

}
