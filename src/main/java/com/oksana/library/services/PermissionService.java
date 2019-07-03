package com.oksana.library.services;

import com.oksana.library.entities.Permission;
import com.oksana.library.repositories.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> findAll(){
       return this.permissionRepository.findAll();
    }

    public Permission save(Permission permission){
       return this.permissionRepository.save(permission);
    }

    public void delete(Permission permission){
        this.permissionRepository.delete(permission);
    }
}
