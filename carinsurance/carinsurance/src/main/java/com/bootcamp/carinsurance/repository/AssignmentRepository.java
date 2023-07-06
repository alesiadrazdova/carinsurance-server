package com.bootcamp.carinsurance.repository;

import com.bootcamp.carinsurance.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {}
