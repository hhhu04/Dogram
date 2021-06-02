package com.example.dogram2.controller;

import com.example.dogram2.dto.Comment;
import com.example.dogram2.dto.Community;
import com.example.dogram2.dto.LikeList;
import com.example.dogram2.dto.User;
import com.example.dogram2.service.CommunityService;
import com.example.dogram2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/feed",produces="application/json; charset=utf-8")
@RequiredArgsConstructor
public class CommunityController {

    private final UserService userService;
    private final CommunityService communityService;

    @PostMapping("/")
    @ResponseBody
    public List<Community> feed(@CookieValue(value="id", required=false) Cookie cookie){
        List<Community> list = null;

        if(!cookie.getValue().isEmpty()){
            try {
                list = communityService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }



    @PostMapping("/{number}")
    @ResponseBody
    public Community feedOne(@CookieValue(value="id", required=false) Cookie cookie,@PathVariable("number") int number){
        Community list = null;

        try {
            list = communityService.one();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @PostMapping("/like")
    public List<LikeList> likeOne(@CookieValue(value="id", required=false) Cookie cookie){
        List<LikeList> list = null;
        try {
            list = communityService.likeLists(102L);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @PostMapping(value = "/newFeed")
    @ResponseBody
    public int create(@RequestParam("img") MultipartFile img, HttpServletRequest dto, Model model,
                      ModelAndView mv,@CookieValue(value="id", required=false) Cookie cookie, HttpServletResponse response) {
        Community community = new Community();
        try {
            System.out.println(dto.toString());
            Long num = userService.checkCookie(cookie.getValue());
            String userImg = userService.findImg(cookie.getValue());
            return communityService.create(img,model,mv,dto,community,num,userImg,response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("asd");
            return -1;
        }

    }

    @PostMapping("/addlike")
    @ResponseBody
    public int addLike(@CookieValue(value="id", required=false) Cookie cookie,@RequestBody LikeList likeList){
        try {
            if(!cookie.getValue().isEmpty()) {
                User user = new User();
                user = userService.userInfo(cookie.getValue());
                communityService.createLike(likeList,user);
                return 1;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }

    }

    @PostMapping("commentAdd")
    @ResponseBody
    public int addComment(@CookieValue(value="id", required=false) Cookie cookie, @RequestBody Comment comment){
        try{
            Long num = userService.checkCookie(cookie.getValue());
            comment.setUserNum(num);
            comment.setUserId(cookie.getValue());
            communityService.createComment(comment);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @PostMapping("commentdel")
    @ResponseBody
    public int delComment(@CookieValue(value="id", required=false) Cookie cookie, @RequestBody Comment comment) {
        try{
            if(userService.checkCookie(cookie.getValue()).equals(comment.getUserNum())) {
                communityService.deleteComment(comment);
                return 1;
            }
            return -2;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @PostMapping("update")
    @ResponseBody
    public int updateCommunity(@RequestParam("img") MultipartFile img, HttpServletRequest dto, Model model,
                               ModelAndView mv,@CookieValue(value="id", required=false) Cookie cookie, HttpServletResponse response) {
        try {
            Community community = new Community();
            System.out.println(dto.toString());
            Long num = userService.checkCookie(cookie.getValue());
            String userImg = userService.findImg(cookie.getValue());
            communityService.updateCommunity(img,model,mv,dto,community,num,userImg,response);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("asd");
            return -1;
        }

    }

    @PostMapping("delete")
    @ResponseBody   //community_num,user_num
    public int deleteCommunity(@RequestBody Community community,@CookieValue(value="id", required=false) Cookie cookie){
        try{
            Long num = userService.checkCookie(cookie.getValue());
            if(community.getNum().equals(num)){
                communityService.delete(community);
                return 1;
            }
            else throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }



}
