package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.Assignment;
import com.bootcamp.carinsurance.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }


    @Transactional
    public void save(Assignment assignment) {
        assignmentRepository.save(assignment);
    }
}
