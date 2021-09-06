package com.example.dogram2.service;

import com.example.dogram2.dto.User;
import com.example.dogram2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class UserService  {

    private final UserRepository userRepository;


    //가입
    public int join(MultipartFile img, Model model, ModelAndView mv, HttpServletRequest file,User user) throws Exception{
        user = user.upLoad(img,model,mv,file,user);

        user.setCreditGrade("댕댕이");
        user.setCreditRating(0);
        userRepository.save(user);
        return 1;
    }

    //계정찾기
    public List<User> findEmail(String email) throws Exception{
        return userRepository.findEmail(email);
    }

    //로그인
    public int login(String userId, String password, HttpServletResponse response, HttpServletRequest request) throws Exception{
        List<User> user = userRepository.checkId(userId,password);
        if(!user.isEmpty()){
            Cookie cookie = new Cookie("id",user.get(0).getId());
            System.out.println(cookie.getValue());
            response.addCookie(cookie);
            System.out.println(user);
            return 1;
        }
        else return -1;
    }

    //로그인 확인
    public Long checkCookie(String userId) throws Exception{
        List<User> user = userRepository.checkCookie(userId);
        Long num = user.get(0).getNum();
        if(num != null) return num;
        else return -1L;
    }

    public String findImg(String userId) throws Exception{
        List<User> user = userRepository.checkCookie(userId);
        String num = user.get(0).getImg();
        if(num != null) return num;
        else return null;
    }


    //아이디중복확인
    public int checkIdOrEmail(String userId) throws Exception{
        List<User> user = userRepository.checkCookie(userId);
        if(user.isEmpty()) return 1;
        else return -1;
    }

    //마이페이지
    public User myPage(User user) throws Exception{
        user = userRepository.findOne(user.getNum());
        return user;
    }

    //유저정보
    public User userInfo(String id) throws Exception{
        List<User> user = new ArrayList<>();
        user = userRepository.checkCookie(id);
        return user.get(0);
    }

    public int update(User user,MultipartFile img, HttpServletRequest file, Model model, ModelAndView mv) throws Exception{
        user = user.upLoad(img, model, mv, file, user);
        userRepository.update(user);
        return 1;
    }


    public void delete(User user) throws Exception {
        //임시로
        user.setPassword("123");
        if(!userRepository.checkPassword(user.getPassword(),user.getNum()).isEmpty()) {
            user = userRepository.findOne(user.getNum());
            userRepository.delete(user);
        }
        else throw new Exception();

    }









}
