package com.planner.server.domain.study_room.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.planner.server.domain.room_chat.entity.RoomChat;
import com.planner.server.domain.room_user.entity.RoomUser;
import com.planner.server.domain.study_category.entity.StudyCategory;
import com.planner.server.domain.user.entity.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "study_room")
@Getter
public class StudyRoom {
    
    @Id
    private UUID id;

    private String name;

    private int maximumNumberOfPeople;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_category_id")
    private StudyCategory studyCategory;

    private Duration studyGoalTime;

    private String roomPassword;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "studyRoom")
    List<RoomUser> roomUsers = new ArrayList<>();

    @OneToMany(mappedBy = "studyRoom")
    List<RoomChat> roomChats = new ArrayList<>();

    public void addRoomUser(RoomUser roomUser) {
        this.roomUsers.add(roomUser);
        roomUser.setStudyRoom(this);
    }

    public void addRoomChat(RoomChat roomChat) {
        this.roomChats.add(roomChat);
        roomChat.setStudyRoom(this);
    }
}