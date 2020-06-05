package com.dbs.userhierarchy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "teamhierarchy")
public class TeamHierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empid")
    private String empId;

    @Column(name = "managerid")
    private String managerId;


}
