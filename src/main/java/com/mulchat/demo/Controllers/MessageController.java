package com.mulchat.demo.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mulchat.demo.Entities.Message;
import com.mulchat.demo.Services.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/sendMessage")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        // Delegate message handling to the service
        Message savedMessage = messageService.sendMessage(message);

        // Return the saved user message (the bot's response is handled internally by the service)
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/{chatId}")
    public List<Message> getAllMessages(@PathVariable Long chatId) {
        return messageService.getAllMessages(chatId);
    }

    @GetMapping("/botResponses")
    public List<Message> getBotResponses(@RequestParam Long chatId, @RequestParam String afterTimestamp) {
        LocalDateTime timestamp = LocalDateTime.parse(afterTimestamp);
        return messageService.getBotResponses(chatId, timestamp);
    }
}
