package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor    // 기본 생성자
@AllArgsConstructor    // 모든 변수의 생성자
@Builder
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // auto_increment
    private int id;

    @Column(nullable = false)
    private String name; // 시설 이름

    @Column(nullable = false)
    private String address;// 주소

    @Column(nullable = false)
    private String latitude; // 위도

    @Column(nullable = false)
    private String longitude; // 경도

    @Lob()
    private String explanation; // 시설 설명

    @Column(nullable = false)
    private int kinds; // 시설 종류 (종류에 따라 아이콘 다르게)

    @Column(nullable = false)
    private String img;
}
