package dogram.dogram.domain.repository;

import dogram.dogram.domain.entity.Community;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommunityRepositoryTest {

    @Autowired
    private CommunityRepository communityRepository;

    @Test
    public void create(){

        Community community = new Community();
        community.setImg("/home/cat/Downloads");
        community.setTitle("샘플");
        community.setBody("내용");
        community.setCommentCount(0);
        community.setLikeCount(0);
        community.setTag("고양이");
        community.setUserNum(1L);
        community.setCreatedAt(LocalDateTime.now());

        Community newCommunity = communityRepository.save(community);


    }




}