package com.mulchat.demo.Services;
import java.beans.JavaBean;
import java.util.List;


public class ChatRequest {
    private String name;
    private Boolean isGroup;
    private List<Long> memberIds;
    public ChatRequest(String name, Boolean isGroup, List<Long> memberIds) {
        this.name = name;
        this.isGroup = isGroup;
        this.memberIds = memberIds;
    }
    public ChatRequest() {
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
    public List<Long> getMemberIds() {
        return memberIds;
    }
    public void setMemberIds(List<Long> memberIds) {
        this.memberIds = memberIds;
    }
    @Override
    public String toString() {
        return "ChatRequest [name=" + name + ", isGroup=" + isGroup + ", memberIds=" + memberIds + ", getIsGroup()="
                + getIsGroup() + ", getMemberIds()=" + getMemberIds() + ", getName()=" + getName() + ", hashCode()="
                + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChatRequest other = (ChatRequest) obj;
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
        if (memberIds == null) {
            if (other.memberIds != null)
                return false;
        } else if (!memberIds.equals(other.memberIds))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((isGroup == null) ? 0 : isGroup.hashCode());
        result = prime * result + ((memberIds == null) ? 0 : memberIds.hashCode());
        return result;
    }

    // Getters and setters
}
