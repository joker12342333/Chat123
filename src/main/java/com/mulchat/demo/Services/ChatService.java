package com.mulchat.demo.Services;

import com.mulchat.demo.Entities.Chat;
import com.mulchat.demo.Entities.Users;
import com.mulchat.demo.Repositories.ChatRepo;
import com.mulchat.demo.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatRepo chatRepository;

    @Autowired
    private UserRepo userRepository;

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Optional<Chat> getChatById(Long id) {
        return chatRepository.findById(id);
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }

    public Chat createChatWithMembers(String chatName, Boolean isGroup, List<Long> userIds) {
        // Create a new chat instance
        Chat chat = new Chat();
        chat.setName(chatName);
        chat.setIsGroup(isGroup);
    
        // Retrieve users from the database
        List<Users> users = userIds.stream()
            .map(userId -> userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"))
            )
            .collect(Collectors.toList());
    
        // Set members of the chat
        chat.setMembers(users);
    
        // Save the chat
        return chatRepository.save(chat);
    }
    
}

    
    
        // Set members of the chat
    

