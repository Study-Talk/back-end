package com.planner.server.domain.study_room.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.planner.server.domain.room_chat.dto.RoomChatRes;
import com.planner.server.domain.study_category.dto.StudyCategoryResDto;
import com.planner.server.domain.study_category.entity.StudyCategory;
import com.planner.server.domain.study_room.entity.StudyRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudyRoomResDto {
    private UUID id;
    private String name;
    private StudyCategoryResDto studyCategoryDto;   // study room을 조회할 때, 카테고리는 항상 포함
    private int maximumNumberOfPeople;
    private Duration studyGoalTime;
    private String roomPassword;
    private UUID masterUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static StudyRoomResDto toDto(StudyRoom entity) {
        StudyCategory studyCategory = entity.getStudyCategory();
        StudyCategoryResDto studyCategoryDto = StudyCategoryResDto.builder()
            .id(studyCategory.getId())
            .name(studyCategory.getName())
            .description(studyCategory.getDescription())
            .build();

        return StudyRoomResDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .studyCategoryDto(studyCategoryDto)
            .maximumNumberOfPeople(entity.getMaximumNumberOfPeople())
            .studyGoalTime(entity.getStudyGoalTime())
            // .roomPassword(entity.getRoomPassword())
            .masterUserId(entity.getMasterUserId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }

    // @Getter
    // @Builder
    // @ToString
    // @AllArgsConstructor
    // @NoArgsConstructor
    // private static class StudyRoomAndStudyCategoryAndRoomUserAndRoomChat {
    //     private UUID id;
    //     private String name;
    //     private StudyCategoryResDto studyCategoryDto;
    //     private int maximumNumberOfPeople;
    //     private Duration studyGoalTime;
    //     private String roomPassword;
    //     private UUID masterUserId;
    //     private LocalDateTime createdAt;
    //     private LocalDateTime updatedAt;
    //     // TODO :: DTO생성되면 넣어야할 객체들
    //     // private List<RoomUserDto> roomUserDtos = new ArrayList<>();
    //     private List<RoomChatRes> roomChatDtos = new ArrayList<>();

    //     public static StudyRoomResDto toDto(StudyRoom entity) {
    //         // StudyCategoryDto studyCategoryDto =
    //         // StudyCategoryDto.toDto(entity.getStudyCategory());
    //         StudyCategory studyCategory = entity.getStudyCategory();
    //         StudyCategoryResDto studyCategoryDto = StudyCategoryResDto.builder()
    //                 .id(studyCategory.getId())
    //                 .name(studyCategory.getName())
    //                 .description(studyCategory.getDescription())
    //                 .build();

    //         List<RoomChatRes> roomChatDtos = entity.getRoomChats().stream().map(chat -> RoomChatRes.toDto(chat))
    //                 .collect(Collectors.toList());

    //         return StudyRoomResDto.builder()
    //                 .id(entity.getId())
    //                 .name(entity.getName())
    //                 .studyCategoryDto(studyCategoryDto)
    //                 .maximumNumberOfPeople(entity.getMaximumNumberOfPeople())
    //                 .studyGoalTime(entity.getStudyGoalTime())
    //                 // .roomPassword(entity.getRoomPassword())
    //                 .masterUserId(entity.getMasterUserId())
    //                 .createdAt(entity.getCreatedAt())
    //                 .updatedAt(entity.getUpdatedAt())
    //                 .roomChatDtos(roomChatDtos)
    //                 .build();
    //     }
    // }
}