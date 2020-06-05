package com.dbs.userhierarchy.util;

import com.dbs.userhierarchy.model.UserAccess;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserAccessResults {

    private List<String> errors;
    private List<UserAccess> userAccessList;

}
