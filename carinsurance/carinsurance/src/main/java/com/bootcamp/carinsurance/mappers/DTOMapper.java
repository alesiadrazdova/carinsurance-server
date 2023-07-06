package com.bootcamp.carinsurance.mappers;

public interface DTOMapper<Model, DTO> {
    DTO mapToDTO(Model model);
    Model mapToModel(DTO dto);
}
