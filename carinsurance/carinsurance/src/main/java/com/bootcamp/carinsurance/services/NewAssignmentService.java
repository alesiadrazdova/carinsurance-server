package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.dto.*;
import com.bootcamp.carinsurance.models.*;
import com.bootcamp.carinsurance.security.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewAssignmentService {
    private final UserDetailsService userDetailsService;
    private final AssignmentService assignmentService;
    private final VehicleInformationService vehicleInformationService;
    private final VehicleConditionService vehicleConditionService;
    private final TypeOfContactService typeOfContactService;

    private final AddressService addressService;
    private final PhoneService phoneService;
    private final ContactService contactService;
    private final StatusService statusService;
    private final ImpactDirectionService impactDirectionService;

    public NewAssignmentService(UserDetailsService userDetailsService,
                                AssignmentService assignmentService,
                                VehicleInformationService vehicleInformationService,
                                VehicleConditionService vehicleConditionService,
                                TypeOfContactService typeOfContactService,
                                AddressService addressService,
                                PhoneService phoneService,
                                ContactService contactService,
                                StatusService statusService, ImpactDirectionService impactDirectionService) {
        this.userDetailsService = userDetailsService;
        this.assignmentService = assignmentService;
        this.vehicleInformationService = vehicleInformationService;
        this.vehicleConditionService = vehicleConditionService;
        this.typeOfContactService = typeOfContactService;
        this.addressService = addressService;
        this.phoneService = phoneService;
        this.contactService = contactService;
        this.statusService = statusService;
        this.impactDirectionService = impactDirectionService;
    }

    public ResponseEntity<String> createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        assignment.setClient(user);
        assignment.setNote(assignmentDTO.getAssignmentNote());
        assignment.setDateOfIncident(assignmentDTO.getDateOfIncident());
        assignment.setStatus(statusService.findById(1));

        if (assignmentDTO.getVehicleInformation() != null) {
            VehicleInformationDTO originalVehicleInformation = assignmentDTO.getVehicleInformation();
            VehicleInformation processedVehicleInformation = new VehicleInformation();
            processedVehicleInformation.setVin(originalVehicleInformation.getVin());
            processedVehicleInformation.setModel(originalVehicleInformation.getModel());
            processedVehicleInformation.setYear(originalVehicleInformation.getYear());
            processedVehicleInformation.setMakeIn(originalVehicleInformation.getMakeIn());
            processedVehicleInformation.setLicensePart(originalVehicleInformation.getLicensePart());
            processedVehicleInformation.setLicenseState(originalVehicleInformation.getLicenseState());
            processedVehicleInformation.setLicenseExpiration(originalVehicleInformation.getLicenseExpiration());
            processedVehicleInformation.setOdometerValue(originalVehicleInformation.getOdometerValue());
            assignment.setVehicleInformation(processedVehicleInformation);
            vehicleInformationService.save(processedVehicleInformation);
        }

        if (assignmentDTO.getVehicleCondition() != null) {
            VehicleConditionDTO originalVehicleCondition = assignmentDTO.getVehicleCondition();
            VehicleCondition processedVehicleCondition = new VehicleCondition();
            processedVehicleCondition.setAssignment(assignment);
            List<ImpactDirection> listForSave = new ArrayList<>();
            if (!originalVehicleCondition.getImpactDirections().isEmpty()) {
                for (ImpactDirectionDTO impactDirectionDTO : originalVehicleCondition.getImpactDirections()) {
                    ImpactDirection impactDirection = new ImpactDirection();
                    impactDirection.setName(NameOfImpactDirection.valueOf(impactDirectionDTO.getName()));
                    impactDirection.setVehicleCondition(processedVehicleCondition);
                    impactDirectionService.save(impactDirection);
                    listForSave.add(impactDirection);
                }
            }
            processedVehicleCondition.setImpact_directions(listForSave);
            processedVehicleCondition.setPhotos(originalVehicleCondition.getPhotos().toString());//заглушка
            assignment.setVehicleCondition(processedVehicleCondition);
            vehicleConditionService.save(processedVehicleCondition);
        }


        if (assignmentDTO.getContacts() != null) {
            List<Phone> listOfPhones = new ArrayList<>();
            List<Address> listOfAddresses = new ArrayList<>();
            List<Contact> processedContactList = new ArrayList<>();
            for (ContactDTO originalContact : assignmentDTO.getContacts()) {

                Contact processedContact = new Contact();
                processedContact.setAssignment(assignment);
                TypeOfContact typeOfContactForSave = typeOfContactService.findByName(originalContact.getTypeOfContact().getNameOfType());
                processedContact.setTypeOfContact(typeOfContactForSave);
                processedContact.setEmail(originalContact.getEmail());

                for (PhoneDTO phone : originalContact.getPhones()) {
                    Phone phoneForSave = new Phone(processedContact,
                            phone.getNumber(),
                            TypeOfPhone.valueOf(phone.getTypeOfPhone()));
                    listOfPhones.add(phoneForSave);
                    phoneService.save(phoneForSave);
                }

                processedContact.setPhones(listOfPhones);

                for (AddressDTO address : originalContact.getAddresses()) {
                    Address addressForSave = new Address(
                            processedContact,
                            address.getCity(),
                            address.getState(),
                            address.getZip(),
                            address.getAddressLine(),
                            TypeOfAddress.valueOf(address.getTypeOfAddress()));
                    listOfAddresses.add(addressForSave);
                    addressService.save(addressForSave);
                }

                contactService.save(processedContact);
            }
            assignment.setContacts(processedContactList);
        }
        assignmentService.save(assignment);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
