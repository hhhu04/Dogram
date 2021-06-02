package com.example.dogram2.repository;

import com.example.dogram2.dto.LikeList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeListRepository {

    private final EntityManager em;

    public int save(LikeList likeList){
        em.persist(likeList);
        return 1;
    }

    public List<LikeList> findNum(Long num){
        return em.createQuery("select l from LikeList l where l.num =: num",LikeList.class)
                .setParameter("num",num)
                .getResultList();
    }

    public List<LikeList> findAdd(LikeList likeList) throws Exception{
        return em.createQuery("select l from LikeList l where l.userNum =: userNum and l.communityNum =:communityNum",LikeList.class)
                .setParameter("userNum",likeList.getUserNum())
                .setParameter("communityNum",likeList.getCommunityNum())
                .getResultList();
    }

    public int delete(LikeList likeList) throws Exception{
        LikeList likeLists = em.find(LikeList.class,likeList.getNum());
        em.remove(likeLists);


        return 1;
    }

    public void de(LikeList likeList)throws Exception{
         em.createQuery("delete from LikeList l where l.num=:num",LikeList.class)
                .setParameter("num",likeList.getNum())
                .executeUpdate();
    }


}
