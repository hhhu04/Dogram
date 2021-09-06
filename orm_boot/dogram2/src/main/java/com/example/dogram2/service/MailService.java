package com.example.dogram2.service;

import com.example.dogram2.dto.Mail;
import com.example.dogram2.dto.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MailService {

    @Autowired
    private UserService userService;

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ShinTest94@gmail.com";

    public void mailSend(User mail) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        List<User> user = userService.findEmail(mail.getEmail());
        System.out.println(user.get(0));
        message.setTo(user.get(0).getEmail());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject("요청하신 계정 정보");
        message.setText("id : "+user.get(0).getId()+"  pw : "+user.get(0).getPassword());

        mailSender.send(message);

    }

}