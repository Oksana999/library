package com.oksana.library.mappers;

import com.oksana.library.dtos.RoleDto;
import com.oksana.library.entities.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final ModelMapper modelMapper;
    private final PermissionMapper permissionMapper;



    public Role mapToEntity(RoleDto roleDto){
      return   this.modelMapper.map(roleDto, Role.class);
    }

    public RoleDto mapToDto(Role role){
        RoleDto result = this.modelMapper.map(role, RoleDto.class);
        result.setPermissionDtoList(role.getPermissions()
                                        .stream()
                                        .map(this.permissionMapper::mapToDto)
                                        .collect(Collectors.toList()));
        return result;
    }
}
