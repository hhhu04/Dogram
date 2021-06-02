package com.example.dogram2.service;

import com.example.dogram2.dto.Comment;
import com.example.dogram2.dto.Community;
import com.example.dogram2.dto.LikeList;
import com.example.dogram2.dto.User;
import com.example.dogram2.repository.CommentRepository;
import com.example.dogram2.repository.CommunityRepository;
import com.example.dogram2.repository.LikeListRepository;
import com.example.dogram2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final LikeListRepository likeListRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    public List<Community> findAll() throws Exception{
        List<Community> list = communityRepository.findAll();
        return list;
    }

    public List<Community> findMy(Long num) throws Exception{
        List<Community> list = communityRepository.findMy(num);
        return list;
    }


    public Community one() throws Exception{
        return communityRepository.findOne(9L);
    }

    public List<LikeList> likeLists(Long num){
        return likeListRepository.findNum(num);
    }


    public int create(MultipartFile img, Model model, ModelAndView mv, HttpServletRequest file,
                      Community community,Long cookie,String userImg, HttpServletResponse response) throws Exception {
        community = community.upLoad(img,model,mv,file,community,cookie,response);
        community.setUserImg(userImg);
        communityRepository.save(community);
        return 1;
    }

    public int createLike(LikeList likeList,User user) throws Exception{
        likeList = likeList.add(likeList,user);
        if(!likeListRepository.findAdd(likeList).isEmpty()){
            likeList = likeListRepository.findAdd(likeList).get(0);
            likeListRepository.delete(likeList);
        }

        else likeListRepository.save(likeList);
        return 1;
    }


    public void createComment(Comment comment) throws Exception{
        commentRepository.save(comment);

    }


    public void deleteComment(Comment comment) throws Exception{
        comment = commentRepository.findOneDel(comment).get(0);
        commentRepository.remove(comment);
    }


    public void updateCommunity(MultipartFile img, Model model, ModelAndView mv, HttpServletRequest file,
                                Community community,Long cookie,String userImg, HttpServletResponse response) throws Exception {

        community = community.upLoad(img,model,mv,file,community,cookie,response);
        community.setUserImg(userImg);
        communityRepository.updateCommunity(community);

    }


    public void delete(Community community) throws Exception {
        community = communityRepository.findOne(community.getNum());
        communityRepository.deleteCommunity(community);
    }



}
