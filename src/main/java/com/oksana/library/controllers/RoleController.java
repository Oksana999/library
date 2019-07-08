package com.oksana.library.controllers;

import com.oksana.library.config.PermissionRequired;
import com.oksana.library.dtos.RoleDto;
import com.oksana.library.entities.Role;
import com.oksana.library.mappers.RoleMapper;
import com.oksana.library.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;
    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @PostMapping
    @PermissionRequired(name = "ROLE_CREATE", description = "Only for Managers")
    public RoleDto createRole(@RequestBody RoleDto roleDto){
       return this.roleMapper.mapToDto(this.roleService.create(this.roleMapper.mapToEntity(roleDto)));
    }

    @GetMapping (value = "/{id}")
    @PermissionRequired(name = "ROLE_GET", description = "Manager")
    public RoleDto findById(@PathVariable Long id){
        Role role = this.roleService.findById(id).orElseThrow(EntityNotFoundException::new);
      return this.roleMapper.mapToDto(role);
    }

    @GetMapping()
    @PermissionRequired(name = "ROLE_GET_ALL")
    public List<Role> getAll(){
       return this.roleService.findAll();
    }

    @PutMapping("/{id}")
    @PermissionRequired(name = "ROLE_UPDATE")
    public Role update(@PathVariable Long id, @RequestBody RoleDto roleDto){
        Role updateRole = this.roleService.update(this.roleMapper.mapToEntity(roleDto));
        return updateRole;
    }

    @DeleteMapping("/{id}")
    @PermissionRequired(name = "ROLE_DELETE")
    public String delete(@PathVariable Long id, @RequestBody RoleDto roleDto){
        Role foundedRole = this.roleService.findById(id).orElseThrow(() -> new RuntimeException("Role was not found"));
        this.roleService.delete(foundedRole);
        return "Role has been successfully deleted ";
    }

}
