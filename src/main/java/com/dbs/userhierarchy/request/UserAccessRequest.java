package com.dbs.userhierarchy.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAccessRequest {

    private String empId;
    private List<String> empList;
}
