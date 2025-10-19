package com.productmanagement.productmanagementapi.mapper;


import com.productmanagement.productmanagementapi.model.dto.OwnerRequest;
import com.productmanagement.productmanagementapi.model.entity.Owner;
import com.productmanagement.productmanagementapi.model.response.OwnerLogInResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toOwner(OwnerRequest ownerRequest);

    OwnerLogInResponse toOwnerLogInResponse(Owner owner);
}
