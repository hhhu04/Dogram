package com.example.dogram2.repository;

import com.example.dogram2.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public int save(User user){
        em.persist(user);
        return 1;
    }

    public User findOne(Long num){
        return em.find(User.class, num);
    }

    public List<User> findEmail(String email){
        return em.createQuery("select u from User u where u.email =:email",User.class)
                .setParameter("email",email)
                .getResultList();
    }

    public List<User> checkId(String id,String password){
        return em.createQuery("select u from User u where u.id =:id and u.password =:password", User.class)
                .setParameter("id",id)
                .setParameter("password",password)
                .getResultList();
    }

    public List<User> checkCookie(String id){
        return em.createQuery("select u from User u where u.id =:id", User.class)
                .setParameter("id",id)
                .getResultList();
    }

    public List<User> findImg(String id){
        return em.createQuery("select u from User u where u.id =:id", User.class)
                .setParameter("id",id)
                .getResultList();
    }

    public void update(User user) throws Exception{
        em.merge(user);
    }

    public List<User> checkPassword(String password,Long num) throws Exception{
        return em.createQuery("select u from User u where u.password =:password and u.num=:num", User.class)
                .setParameter("password",password)
                .setParameter("num",num)
                .getResultList();
    }

    public void delete(User user) throws Exception{
        em.remove(user);
    }


}
