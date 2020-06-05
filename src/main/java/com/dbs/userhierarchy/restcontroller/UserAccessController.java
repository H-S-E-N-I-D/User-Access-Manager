package com.dbs.userhierarchy.restcontroller;

import com.dbs.userhierarchy.request.UserAccessRequest;
import com.dbs.userhierarchy.response.UserAccessResponse;
import com.dbs.userhierarchy.service.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserAccessController {

    @Autowired
    private UserAccessService userAccessService;

    @PostMapping("/access/add")
    public ResponseEntity<UserAccessResponse> addUserAccess(@RequestBody UserAccessRequest request) {
        UserAccessResponse userAccessResponse = userAccessService.addUserAccess(request);
        return ResponseEntity.ok(userAccessResponse);
    }

    @PostMapping("/access/addAll")
    public ResponseEntity<UserAccessResponse> addAllUserAccess(@RequestBody UserAccessRequest request) {
        UserAccessResponse userAccessResponse = userAccessService.addAllUserAccess(request);
        return ResponseEntity.ok(userAccessResponse);
    }
}
