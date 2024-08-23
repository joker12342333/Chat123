package com.mulchat.demo.Repositories;

import java.beans.JavaBean;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulchat.demo.Entities.Chat;
import com.mulchat.demo.Entities.Users;
@JavaBean
public interface UserRepo extends JpaRepository<Users, Long> {

    
}
