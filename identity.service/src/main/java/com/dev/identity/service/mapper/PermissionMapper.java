package com.dev.identity.service.mapper;

import com.dev.identity.service.dto.request.PermissionRequest;
import com.dev.identity.service.dto.response.PermissionResponse;
import com.dev.identity.service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
