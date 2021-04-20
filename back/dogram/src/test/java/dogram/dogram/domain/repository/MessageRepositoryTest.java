package dogram.dogram.domain.repository;

import dogram.dogram.domain.entity.MessageLogs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MessageRepositoryTest {

    @Autowired
    private MessageLogsRepository messageRepository;

    @Test
    public void create(){

        MessageLogs message = new MessageLogs();
        message.setCreatedAt(LocalDateTime.now());
        message.setWriterNum(1L);
        message.setReaderNum(1L);
        message.setMessage("아무말");
        message.setMessageStatus("일반");

        MessageLogs newMessage = messageRepository.save(message);

    }

}