package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.dto.*;
import com.bootcamp.carinsurance.mappers.AssignmentMapper;
import com.bootcamp.carinsurance.models.*;
import com.bootcamp.carinsurance.security.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewAssignmentService {

    private final AssignmentService assignmentService;
    private final VehicleInformationService vehicleInformationService;
    private final VehicleConditionService vehicleConditionService;
    private final TypeOfContactService typeOfContactService;
    private final AddressService addressService;
    private final PhoneService phoneService;
    private final ContactService contactService;
    private final StatusService statusService;
    private AssignmentMapper assignmentMapper = new AssignmentMapper();
    private ModelMapper modelMapper = new ModelMapper();

    public NewAssignmentService(AssignmentService assignmentService,
                                VehicleInformationService vehicleInformationService,
                                VehicleConditionService vehicleConditionService,
                                TypeOfContactService typeOfContactService,
                                AddressService addressService,
                                PhoneService phoneService,
                                ContactService contactService,
                                StatusService statusService) {
        this.assignmentService = assignmentService;
        this.vehicleInformationService = vehicleInformationService;
        this.vehicleConditionService = vehicleConditionService;
        this.typeOfContactService = typeOfContactService;
        this.addressService = addressService;
        this.phoneService = phoneService;
        this.contactService = contactService;
        this.statusService = statusService;
    }

    public ResponseEntity<String> createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment =assignmentMapper.mapToModel(assignmentDTO);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        assignment.setClient(user);
        assignment.setStatus(statusService.findById(1));

        if (assignmentDTO.getVehicleInformation() != null) {
            VehicleInformationDTO originalVehicleInformation = assignmentDTO.getVehicleInformation();
            VehicleInformation processedVehicleInformation = modelMapper.map(originalVehicleInformation, VehicleInformation.class);
            assignment.setVehicleInformation(processedVehicleInformation);
            vehicleInformationService.save(processedVehicleInformation);
        }

        if (assignmentDTO.getVehicleCondition() != null) {
            VehicleConditionDTO originalVehicleCondition = assignmentDTO.getVehicleCondition();
            VehicleCondition processedVehicleCondition = modelMapper.map(originalVehicleCondition, VehicleCondition.class);
            processedVehicleCondition.setAssignment(assignment);
            List<ImpactDirection> listForSave = new ArrayList<>();
            if (!originalVehicleCondition.getImpactDirections().isEmpty()) {
                for (ImpactDirectionDTO impactDirectionDTO : originalVehicleCondition.getImpactDirections()) {
                    ImpactDirection impactDirection = modelMapper.map(impactDirectionDTO, ImpactDirection.class);
                    listForSave.add(impactDirection);
                }
            }
            processedVehicleCondition.setImpact_directions(listForSave);
            processedVehicleCondition.setPhotos(originalVehicleCondition.getPhotos().toString());//заглушка
            vehicleConditionService.save(processedVehicleCondition);
            assignment.setVehicleCondition(processedVehicleCondition);
        }

        if (assignmentDTO.getContacts() != null) {
            List<Phone> listOfPhones = new ArrayList<>();
            List<Address> listOfAddresses = new ArrayList<>();
            List<Contact> processedContactList = new ArrayList<>();
            for (ContactDTO originalContact : assignmentDTO.getContacts()) {
                Contact processedContact = modelMapper.map(originalContact, Contact.class);
                processedContact.setAssignment(assignment);
                TypeOfContact typeOfContactForSave = typeOfContactService.findByName(originalContact.getTypeOfContact().getNameOfType());
                processedContact.setTypeOfContact(typeOfContactForSave);
                for (PhoneDTO phone : originalContact.getPhones()) {
                    Phone phoneForSave = modelMapper.map(phone, Phone.class);
                    listOfPhones.add(phoneForSave);
                    phoneService.save(phoneForSave);
                }

                processedContact.setPhones(listOfPhones);

                for (AddressDTO address : originalContact.getAddresses()) {
                    Address addressForSave = modelMapper.map(address, Address.class);
                    listOfAddresses.add(addressForSave);
                    addressService.save(addressForSave);
                }
                contactService.save(processedContact);
            }
            assignment.setContacts(processedContactList);
        }
        assignmentService.save(assignment);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
