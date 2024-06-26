package com.example.waplan.goal.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "제3목표")
@Getter
@Setter
public class ThirdGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 15)
    private String title;

    @Column(name = "newTitle", nullable = true, length = 15)
    private String newTitle;

    @Column(name = "color", nullable = false, length = 15)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "성취도", nullable = false)
    private AchievementLevel achievementLevel;

    @ManyToOne
    @JoinColumn(name = "second_goal_id", referencedColumnName = "id")
    private SecondGoal secondGoal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_date_id", referencedColumnName = "id")
    private GoalDate goalDates;
}
