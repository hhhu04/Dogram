package com.example.dogram2.repository;

import com.example.dogram2.dto.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment){
        comment.setCreatedAt(LocalDateTime.now());
        em.persist(comment);
    }

    public List<Comment> findList(Long num){
        return em.createQuery("select c from Comment c where c.communityNum=:communityNum",Comment.class)
                .setParameter("communityNum",num)
                .getResultList();
    }

    public List<Comment> findOneDel(Comment comment){
        return em.createQuery("select c from Comment c where c.num=:num and c.userNum=:userNum and c.communityNum=:communityNum",Comment.class)
                .setParameter("num",comment.getNum())
                .setParameter("userNum",comment.getUserNum())
                .setParameter("communityNum",comment.getCommunityNum())
                .getResultList();
    }


    public void remove(Comment comment){
        em.remove(comment);
    }

}
