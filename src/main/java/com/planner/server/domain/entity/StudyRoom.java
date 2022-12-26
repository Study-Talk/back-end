package com.planner.server.domain.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoom {

    @Id
    @NotNull
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    private int maximumNumberOfPeople;

    private LocalTime studyGoalTime;

    private String roomPassword;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_category_id")
    private StudyCategory studyCategory;

    @OneToMany(mappedBy = "studyRoom")
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public void addRoomUser(RoomUser roomUser){
        this.roomUsers.add(roomUser);
        roomUser.setStudyRoom(this);
    }

    public void addChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
        chatRoom.setStudyRoom(this);
    }

}