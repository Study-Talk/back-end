package com.planner.server.domain.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.planner.server.domain.attendance_check.entity.AttendanceCheck;
import com.planner.server.domain.friend.entity.Friend;
import com.planner.server.domain.record.entity.Record;
import com.planner.server.domain.room_user.entity.RoomUser;
import com.planner.server.domain.study_goal.entity.StudyGoal;
import com.planner.server.domain.study_room.entity.StudyRoom;

import lombok.Getter;

@Entity
@Table(name = "user")
@Getter
public class User {

    @Id
    private UUID id;

    private String username;

    private String password;

    private UUID salt;

    private String roles;

    private String profileName;

    private int profileAge;

    private boolean friendAcceptance;

    private boolean alarmPermission;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    List<Record> records = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<StudyGoal> studyGoals = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<AttendanceCheck> attendanceChecks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<RoomUser> roomUsers = new ArrayList<>();

    // friend의 friend
    @OneToMany(mappedBy = "user")
    List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<StudyRoom> studyRooms = new ArrayList<>();

    public void addStudyRoom(StudyRoom studyRoom) {
        this.studyRooms.add(studyRoom);
        studyRoom.setUser(this);
    }

    public void addRoomUser(RoomUser roomUser) {
        this.roomUsers.add(roomUser);
        roomUser.setUser(this);
    }

    public void addRecord(Record record) {
        this.records.add(record);
        record.setUser(this);
    }

    public void addStudyGoal(StudyGoal studyGoal) {
        this.studyGoals.add(studyGoal);
        studyGoal.setUser(this);
    }

    public void addAttendanceCheck(AttendanceCheck attendanceCheck) {
        this.attendanceChecks.add(attendanceCheck);
        attendanceCheck.setUser(this);
    }

    public void addFriend(Friend friend) {
        this.friends.add(friend);
        friend.setUser(this);
    }
}