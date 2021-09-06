package com.example.dogram2.controller;

import com.example.dogram2.dto.Mail;
import com.example.dogram2.dto.User;
import com.example.dogram2.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpClient;

@Controller
@AllArgsConstructor
public class BaseController {

    private final MailService mailService;

    @GetMapping("/mail")
    public String disMail(){
        return "mail";
    }

    @PostMapping("/mail")
    @ResponseBody
    public String sendMail(@RequestBody User email){
        try {
            mailService.mailSend(email);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:http://localhost:8080/mail";
        }
        return "redirect:http://localhost:8080/#/";
    }


    @GetMapping("/")
    public String home(){
        return "app";
    }

    @GetMapping("/#/")
    public String intro2() {
        return "app";
    }


    @GetMapping("#/feed")
    public String feed() {
        return "app";
    }

    @GetMapping("#/auth/login")
    public String login() {
        return "app";
    }


    @GetMapping("#/auth/join")
    public String join() {
        return "app";
    }


    @GetMapping("#/store")
    public String store() {
        return "app";
    }


    @GetMapping("#/mypage")
    public String mypage() {
        return "app";
    }

}
