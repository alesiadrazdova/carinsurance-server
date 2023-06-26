package com.bootcamp.carinsurance.repository;

import com.bootcamp.carinsurance.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Integer> {

}
