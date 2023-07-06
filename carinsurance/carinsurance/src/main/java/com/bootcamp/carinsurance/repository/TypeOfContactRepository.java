package com.bootcamp.carinsurance.repository;

import com.bootcamp.carinsurance.models.TypeOfContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfContactRepository extends JpaRepository<TypeOfContact,Integer> {

    Optional<TypeOfContact> findByName(String name);
}
