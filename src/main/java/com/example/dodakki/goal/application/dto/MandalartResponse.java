package com.example.dodakki.goal.application.dto;

import com.example.dodakki.Photo.domain.Photo;
import com.example.dodakki.goal.domain.Mandalart;
import com.example.dodakki.goal.domain.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MandalartResponse {
    private Long id;
    private String name;
    private Integer dDay;
    private Status status;
    private List<Photo> photoList;
    private String description;
    private Double attainment;

    public static MandalartResponse of(Mandalart mandalart) {
        double attainmentRate;
        if(mandalart.getGoalCount() == 0) {
            attainmentRate = 0;
        }
        else {
            attainmentRate = (double) mandalart.getAttainmentCount() / mandalart.getGoalCount();
        }
        return new MandalartResponse(mandalart.getId(), mandalart.getName(), Period.between(LocalDate.now(), mandalart.getGoalDate().getDate()).getDays(), mandalart.getStatus(), mandalart.getPhotoList(), mandalart.getDescription(), attainmentRate);
    }
}
