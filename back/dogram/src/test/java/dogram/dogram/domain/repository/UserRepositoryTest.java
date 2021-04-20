package dogram.dogram.domain.repository;

import dogram.dogram.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

        User user = new User();
        user.setId("123");
        user.setPassword("123");
        user.setPetName("망고");
        user.setEmail("123@123");
        user.setPhoneNumber("010-0000-0000");
        user.setImg("/home/cat/Downloads");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreditRating(0);
        user.setCreditGrade("댕댕이");

        User newUser = userRepository.save(user);

    }

}