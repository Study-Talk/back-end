package com.planner.server.domain.attendance_check.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AttendanceReqDto {

    private UUID userId;
}
