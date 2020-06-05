package com.dbs.userhierarchy.repository;

import com.dbs.userhierarchy.model.TeamHierarchy;
import com.dbs.userhierarchy.model.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {

    public List<UserAccess> findByEmpId(String empId);

}
