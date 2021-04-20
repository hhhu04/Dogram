package dogram.dogram.domain.repository;

import dogram.dogram.domain.entity.LikeList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikeListRepositoryTest {

    @Autowired
    private LikeListRepository likeListRepository;

    @Test
    public void create(){

        LikeList ll = new LikeList();
        ll.setCommunityNum(1L);
        ll.setUserNum(1L);


    }


}