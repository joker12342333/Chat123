package com.mulchat.demo.Controllers;


import java.beans.JavaBean;
import java.net.CacheRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mulchat.demo.Entities.Chat;
import com.mulchat.demo.Services.ChatService;

import org.springframework.web.bind.annotation.PostMapping;

import com.mulchat.demo.Entities.Users;
@RestController
@RequestMapping("/chats")
@JavaBean
public class ChatController {
    @Autowired
    private ChatService chatService;

@PostMapping("/createChatWithMembers")

public ResponseEntity<Chat> createChatWithMembers(@RequestBody Chat chatRequest) {
    // Extract the necessary fields from chatRequest
    String chatName = chatRequest.getName();
    Boolean isGroup = chatRequest.getIsGroup();
    
    // Convert list of Users to list of Long (user IDs)
    List<Long> userIds = chatRequest.getMembers().stream()
                                    .map(Users::getId)
                                    .collect(Collectors.toList());

    // Call the service method with the extracted fields and get the created chat
    Chat createdChat = chatService.createChatWithMembers(chatName, isGroup, userIds);

    // Return the created Chat object in the response
    return ResponseEntity.ok(createdChat);
}

    @GetMapping("/{id}")
    public Optional<Chat> getChatById(@PathVariable Long id) {
        return chatService.getChatById(id);
    }

    @PostMapping
    public Chat createChat(@RequestBody Chat chat) {
        return chatService.createChat(chat);
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
    }
    
}
