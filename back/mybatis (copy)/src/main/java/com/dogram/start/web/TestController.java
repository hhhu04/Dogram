package com.dogram.start.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogram.start.service.test.TestService;

@Controller
public class TestController {
 
    @Autowired
    private TestService service;
    
    @RequestMapping("/selectNow.do")
    public void selectNow() {
        String result = service.selectNow();
        System.out.println(result);
    }    
}
