package com.mulchat.demo.Entities;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.mulchat.demo.Entities.Users;

@Entity
@Table(name = "CHAT")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")

    private String name;

    @Column(name = "IS_GROUP")
    private Boolean isGroup;

    @ManyToMany
    @JoinTable(
        name = "chat_members",
        joinColumns = @JoinColumn(name = "chat_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    
    private List<Users> members;

    public Chat(Long id, String name, Boolean isGroup, List<Users> members) {
        this.id = id;
        this.name = name;
        this.isGroup = isGroup;
        this.members = members;
    }

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public List<Users> getMembers() {
        return members;
    }

    public void setMembers(List<Users> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Chat [id=" + id + ", name=" + name + ", isGroup=" + isGroup + ", members=" + members + ", getId()="
                + getId() + ", getIsGroup()=" + getIsGroup() + ", getMembers()=" + getMembers() + ", getName()="
                + getName() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
                + super.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Chat other = (Chat) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (isGroup == null) {
            if (other.isGroup != null)
                return false;
        } else if (!isGroup.equals(other.isGroup))
            return false;
        if (members == null) {
            if (other.members != null)
                return false;
        } else if (!members.equals(other.members))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((isGroup == null) ? 0 : isGroup.hashCode());
        result = prime * result + ((members == null) ? 0 : members.hashCode());
        return result;
    }

    // Getters and Setters
}
