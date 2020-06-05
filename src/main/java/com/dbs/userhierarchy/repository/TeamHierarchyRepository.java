package com.dbs.userhierarchy.repository;

import com.dbs.userhierarchy.model.TeamHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamHierarchyRepository extends JpaRepository<TeamHierarchy, Long> {

    public Optional<TeamHierarchy> findByEmpId(String empId);
}
