package com.example.dogram2.repository;

import com.example.dogram2.dto.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityRepository {

    private final EntityManager em;

    public void save(Community community) throws Exception{
        em.persist(community);
    }

    public List<Community> findAll() throws Exception{
        return em.createQuery("select c from Community c order by c.num desc ", Community.class)
                .getResultList();
    }

    public Community findOne(Long community_num) throws Exception{
        return em.find(Community.class,community_num);
    }

    public List<Community> findMy(Long userNum) throws Exception{
        return em.createQuery("select c from Community c where c.userNum=:userNum order by c.num desc ", Community.class)
                .setParameter("userNum",userNum)
                .getResultList();
    }

    public int updateCommunity(Community community){
        em.merge(community);
        return 1;
    }

    public void deleteCommunity(Community community) throws Exception{
        em.remove(community);
    }




}
