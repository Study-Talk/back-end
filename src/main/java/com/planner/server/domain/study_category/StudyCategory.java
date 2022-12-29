package com.planner.server.domain.study_category;

import com.planner.server.domain.study_room.StudyRoom;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyCategory {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cid;

    @Type(type = "uuid-char")
    private UUID id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "studyCategory")
    private List<StudyRoom> studyRooms = new ArrayList<>();

    public void addStudyRoom(StudyRoom studyRoom){
        this.studyRooms.add(studyRoom);
        studyRoom.setStudyCategory(this);
    }

}
