package com.example.dogram2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!"); // 키값과 밸류값을 지정해서 model에 담아서 view에 넘긴다.
        return "test";
    }

    @GetMapping("ge")
    public String ge(){
        return "newfeed";
    }


}
