package com.dbs.userhierarchy.repository;

import com.dbs.userhierarchy.model.User;
import com.dbs.userhierarchy.model.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmpId(String empId);

}
