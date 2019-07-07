package com.oksana.library.mappers;

import com.oksana.library.dtos.PermissionDto;
import com.oksana.library.entities.Permission;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Mapper
@RequiredArgsConstructor
public class PermissionMapper {
    private final ModelMapper modelMapper;

    public PermissionDto mapToDto(Permission permission){
       return this.modelMapper.map(permission, PermissionDto.class);
    }
     public Permission mapToEntity(PermissionDto permissionDto){
      return this.modelMapper.map(permissionDto, Permission.class);
     }
}
