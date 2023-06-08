package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.Role;
import com.bootcamp.carinsurance.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role searchById(Integer id){
        Optional<Role> foundRole = roleRepository.findById(id);
        return foundRole.orElse(null);
    }

    public Role searchByName(String name){
        Optional<Role> foundRole = roleRepository.findByName(name);
        return foundRole.orElse(null);
    }
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public void update(int id, Role updatedRole) {
        updatedRole.setRoleId(id);
        roleRepository.save(updatedRole);
    }

    @Transactional
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

}
