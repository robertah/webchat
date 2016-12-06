package org.chat.model.db;
// Generated 29-nov-2016 10.27.11 by Hibernate Tools 4.3.1

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.chat.model.LocalDatabase;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

    private String username;
    private String password;
    private boolean online;

    private Map<User, LinkedList<Message>> userMessages;
    private Map<User, LinkedList<Message>> bufferUserMessages;

    private List<Message> chatRoomMessages;
    private List<Message> chatRoombufferMessages;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Map<User, LinkedList<Message>> getUserMessages() {
        if (userMessages == null) {
            userMessages = new HashMap<>();
        }
        return userMessages;
    }

    public Map<User, LinkedList<Message>> getBufferUserMessages() {
        if (bufferUserMessages == null) {
            bufferUserMessages = new HashMap<>();
        }
        return bufferUserMessages;
    }

    public List<Message> getChatRoomMessages() {
        if (chatRoomMessages == null) {
            chatRoomMessages = new LinkedList<>();
        }
        return chatRoomMessages;
    }

    public void setChatRoomMessages(List<Message> chatRoomMessages) {
        this.chatRoomMessages = chatRoomMessages;
    }

    public List<Message> getChatRoombufferMessages() {
        if (chatRoombufferMessages == null) {
            chatRoombufferMessages = new LinkedList<>();
        }
        return chatRoombufferMessages;
    }

    public void setChatRoombufferMessages(List<Message> chatRoombufferMessages) {
        this.chatRoombufferMessages = chatRoombufferMessages;
    }

    public int unreadMessages(String user) {
        int count = 0;

        if (!user.equalsIgnoreCase("GROUP") && this.getBufferUserMessages().get(LocalDatabase.getUsers().get(user.toUpperCase())) != null) {
            for (Message m : this.getBufferUserMessages().get(LocalDatabase.getUsers().get(user.toUpperCase()))) {
                if (!m.getSender().equalsIgnoreCase(this.getUsername())) {
                    count++;
                }
            }
        } else if (user.equalsIgnoreCase("GROUP") && this.getChatRoombufferMessages() != null) {
            for (Message m : this.getChatRoombufferMessages()) {
                if (!m.getSender().equalsIgnoreCase(this.getUsername())) {
                    count++;
                }
            }
        }

        return count;
    }


}