package com.dbs.userhierarchy.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAccessResponse {
    /*
    Success/Failed
     */
    private String status;
    private List<String> errors;
    private String message;



    public UserAccessResponse() {
    }
}
