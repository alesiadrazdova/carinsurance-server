package com.bootcamp.carinsurance.repository;

import com.bootcamp.carinsurance.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {
    Optional<Status> findByName(String name);
    Optional<Status> findByStatusId(String id);
}
