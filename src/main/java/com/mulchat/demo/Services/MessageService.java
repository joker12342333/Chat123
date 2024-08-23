package com.mulchat.demo.Services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.mulchat.demo.Entities.Chat;
import com.mulchat.demo.Entities.Message;
import com.mulchat.demo.Entities.Users;
import com.mulchat.demo.Repositories.ChatRepo;
import com.mulchat.demo.Repositories.MessageRepo;
import com.mulchat.demo.Repositories.UserRepo;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepo messageRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ChatRepo chatRepository;

    @Autowired
    private GeminiService geminiService;

    @Transactional
    public Message sendMessage(Message message) {
        logger.info("Received message from user: {}", message.getSender().getId());

        // Ensure chat and sender are set
        if (message.getChat() != null && message.getSender() != null) {
            Chat chat = chatRepository.findById(message.getChat().getId())
                                    .orElseThrow(() -> new RuntimeException("Chat not found"));
            Users sender = userRepository.findById(message.getSender().getId())
                                        .orElseThrow(() -> new RuntimeException("User not found"));

            if (message.getTimestamp() != null) {
                message.setTimestamp(LocalDateTime.now());
            }

            message.setChat(chat);
            message.setSender(sender);
        }

        Message savedMessage = messageRepository.save(message);

        if (savedMessage.getSender().getIsBot()) {
            try {
                logger.info("Generating response for bot: {}", savedMessage.getSender().getId());

                String response = geminiService.generateBotResponse(
                    savedMessage.getChat(), 
                    savedMessage.getSender(), 
                    savedMessage.getMessageText()
                );

                if (response != null) {
                    logger.info("Bot response generated successfully: {}", response);

                    Message botResponse = new Message();
                    botResponse.setSender(savedMessage.getSender());
                    botResponse.setMessageText(response);
                    botResponse.setTimestamp(LocalDateTime.now());
                    botResponse.setChat(savedMessage.getChat());

                    messageRepository.save(botResponse);
                }
            } catch (WebClientResponseException.TooManyRequests e) {
                logger.warn("Rate limit exceeded for bot: {}", savedMessage.getSender().getId(), e);
                throw new RuntimeException("Bot is currently busy. Please try again later.");
            } catch (IOException e) {
                logger.error("IOException occurred while generating bot response", e);
                throw new RuntimeException("An error occurred while generating bot response. Please try again later.");
            }
        }

        return savedMessage;}
    

    public List<Message> getAllMessages(Long chatId) {
        return messageRepository.findAllByChatId(chatId);
    }

    public List<Message> getBotResponses(Long chatId, LocalDateTime timestamp) {
        return messageRepository.findAllByChatIdAndTimestampAfter(chatId, timestamp);
    }
}
