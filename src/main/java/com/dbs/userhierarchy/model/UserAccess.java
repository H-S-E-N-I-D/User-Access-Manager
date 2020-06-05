package com.dbs.userhierarchy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "useraccess")
public class UserAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empid")
    private String empId;

    @Column(name = "accesskey")
    private String accessKey;

    @Column(name = "country")
    private String country;

    @Column(name = "subuser")
    private String subuser;

    @Column(name = "subuser_accesskey")
    private String subuserAccesskey;

    @Column(name = "subuser_country")
    private String subuserCountry;

    public UserAccess(String empId, String accessKey, String country, String subuser, String subuserAccesskey, String subuserCountry) {
        this.empId = empId;
        this.accessKey = accessKey;
        this.country = country;
        this.subuser = subuser;
        this.subuserAccesskey = subuserAccesskey;
        this.subuserCountry = subuserCountry;
    }

    public UserAccess() {
    }
}
