package com.example.besteducation.v1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_grade")
public class Grade {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double score;

}
