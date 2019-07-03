package com.oksana.library.services;

import com.oksana.library.entities.Role;
import com.oksana.library.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role create(Role role){
       return this.roleRepository.save(role);
    }

    public Role update(Role role){
       return this.roleRepository.save(role);
    }

    public Optional<Role> findById(Long id){
        Optional<Role> roleById = this.roleRepository.findById(id);
        return roleById;
    }
    public List<Role> findAll(){
       return this.roleRepository.findAll();
    }

    public void delete(Role role){
        this.roleRepository.delete(role);
    }
}
