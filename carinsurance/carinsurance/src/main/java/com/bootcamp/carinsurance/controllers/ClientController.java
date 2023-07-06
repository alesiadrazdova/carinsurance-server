package com.bootcamp.carinsurance.controllers;

import com.bootcamp.carinsurance.dto.AssignmentDTO;
import com.bootcamp.carinsurance.models.NameOfImpactDirection;
import com.bootcamp.carinsurance.models.TypeOfAddress;
import com.bootcamp.carinsurance.models.TypeOfContact;
import com.bootcamp.carinsurance.models.TypeOfPhone;
import com.bootcamp.carinsurance.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private  final NewAssignmentService newAssignmentService;
    private final TypeOfContactService typeOfContactService;

    public ClientController(NewAssignmentService newAssignmentService, TypeOfContactService typeOfContactService) {
        this.newAssignmentService = newAssignmentService;
        this.typeOfContactService = typeOfContactService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        return newAssignmentService.createAssignment(assignmentDTO);
    }

    @RequestMapping(value = "/impact_directions", method = RequestMethod.GET)
    public ResponseEntity<NameOfImpactDirection[]> impactDirectionsIndex() {
        return ResponseEntity.status(HttpStatus.OK).body(NameOfImpactDirection.values());
    }

    @RequestMapping(value = "/types_of_contact", method = RequestMethod.GET)
    public ResponseEntity<List<TypeOfContact>> typesOfContactIndex() {
        return ResponseEntity.status(HttpStatus.OK).body(typeOfContactService.findAll());
    }

    @RequestMapping(value = "/types_of_phone", method = RequestMethod.GET)
    public ResponseEntity<TypeOfPhone[]> typesOfPhoneIndex() {
        return ResponseEntity.status(HttpStatus.OK).body(TypeOfPhone.values());
    }

    @RequestMapping(value = "/types_of_address", method = RequestMethod.GET)
    public ResponseEntity<TypeOfAddress[]> typesOfAddressIndex() {
        return ResponseEntity.status(HttpStatus.OK).body(TypeOfAddress.values());
    }
}
