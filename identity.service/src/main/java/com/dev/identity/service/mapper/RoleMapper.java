package com.dev.identity.service.mapper;

import com.dev.identity.service.dto.request.RoleRequest;
import com.dev.identity.service.dto.response.RoleResponse;
import com.dev.identity.service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
