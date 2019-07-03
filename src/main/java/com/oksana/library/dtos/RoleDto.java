package com.oksana.library.dtos;

import com.oksana.library.entities.Permission;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDto {
    private Long id;
    private String name;
    private List<PermissionDto> permissionDtoList = new ArrayList<>();
}
