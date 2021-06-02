package com.example.dogram2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"list","clist"})
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "community_num")
    private Long num;

    private String img;

    private String title;

    private String content;

    private int commentCount;

    private int likeCount;

    private String category;

    private Long userNum;

    @CreatedDate
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "communityNum")
    private List<LikeList> list = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "communityNum")
    private List<Comment> clist = new ArrayList<>();

    private String userImg;

    //새게시글정보입력
    public Community upLoad(MultipartFile img, Model model, ModelAndView mv, HttpServletRequest file, Community community,Long cookie, HttpServletResponse response) throws Exception{

        file.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        title = file.getParameter("title");
        content = file.getParameter("content");
        category = file.getParameter("category");


        System.out.println(title);

        community.setImg(imgSet(img, mv,model));

        community.setUserNum(cookie);
        community.setLikeCount(0);
        community.setCommentCount(0);
        community.setTitle(title);
        community.setContent(content);
        community.setCategory(category);
        community.setAddress(address);

        return community;
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
