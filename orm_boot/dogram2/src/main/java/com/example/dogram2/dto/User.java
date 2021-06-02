package com.example.dogram2.dto;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"community"})
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_num")
    private Long num;

    private String id;

    private String password;

    private String email;

    private String address;

    private String phoneNumber;

    private String name;

    private String img;

    private int creditRating;

    private String creditGrade;

    @CreatedDate
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userNum")
//    private List<Community> community = new ArrayList<>();


    //로직

    //가입정보입력
    public User upLoad(MultipartFile img, Model model, ModelAndView mv, HttpServletRequest file,User user) throws Exception{

        id = file.getParameter("id");
        password = file.getParameter("password");
        email = file.getParameter("email");
        name = file.getParameter("name");
        phoneNumber = file.getParameter("phoneNumber");

//        id = new String(id.getBytes("8859_1"),"utf-8");
//        password = new String(password.getBytes("8859_1"),"utf-8");
//        email = new String(email.getBytes("8859_1"),"utf-8");
//        name = new String(name.getBytes("8859_1"),"utf-8");

        user.setImg(imgSet(img, mv,model));

        user.setId(id);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);

        return user;
    }


    //사진저장
    public String imgSet(MultipartFile file, ModelAndView mv, Model model) throws Exception{

        Date dt = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyMMddHHmmss");

        String imgName = date.format(dt)+".jpg";

        file.getOriginalFilename();
        if(!file.getOriginalFilename().isEmpty()) {
            file.transferTo(new File("/home/cat/web/dogram2/src/main/resources/static/img", imgName));
            model.addAttribute("msg", "File uploaded successfully.");
            img = "img/"+imgName;
            System.out.println("99");
        }else {
            model.addAttribute("msg", "Please select a valid mediaFile..");
            img = "-1";
            System.out.println("4");
        }
        System.out.println("4");


        return img;
    }


}
