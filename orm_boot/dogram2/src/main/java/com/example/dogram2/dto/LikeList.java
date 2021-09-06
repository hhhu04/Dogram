package com.example.dogram2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private Long communityNum;

    private Long userNum;

    private String id;

    public LikeList add(LikeList likeList,User user) throws Exception{
        likeList.setId(user.getId());
        likeList.setUserNum(user.getNum());
        return  likeList;
    }


}
