package com.dbs.userhierarchy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accesskey")
    private String accessKey;

    @Column(name = "country")
    private String country;

    @Column(name = "empid")
    private String empId;


}
