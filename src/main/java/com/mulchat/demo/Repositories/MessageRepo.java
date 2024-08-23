package com.mulchat.demo.Repositories;

import java.beans.JavaBean;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulchat.demo.Entities.Message;
@JavaBean
public interface MessageRepo extends JpaRepository<Message, Long> {

    public List<Message> findAllByChatId(Long chatId);

    public List<Message> findAllByChat_Id(Long chatId);

    public List<Message> findAllByChatIdAndTimestampAfter(Long chatId, LocalDateTime afterTimestamp);

}