package com.bootcamp.carinsurance.mappers;

import com.bootcamp.carinsurance.dto.AssignmentDTO;
import com.bootcamp.carinsurance.models.Assignment;

public class AssignmentMapper implements DTOMapper<Assignment, AssignmentDTO>{
    @Override
    public AssignmentDTO mapToDTO(Assignment assignment) {
        return null;
    }

    @Override
    public Assignment mapToModel(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setNote(assignmentDTO.getAssignmentNote());
        assignment.setDateOfIncident(assignmentDTO.getDateOfIncident());
        return assignment;
    }
}
