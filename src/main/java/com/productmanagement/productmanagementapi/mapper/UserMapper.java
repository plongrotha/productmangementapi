package com.productmanagement.productmanagementapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.productmanagement.productmanagementapi.model.dto.UserDTO;
import com.productmanagement.productmanagementapi.model.dto.UserUpdateDto;
import com.productmanagement.productmanagementapi.model.entity.User;
import com.productmanagement.productmanagementapi.model.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "dateOfBirth", source = "dob")
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userName", ignore = true)
    User toUserEntity(UserDTO dto);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "dateOfBirth", source = "dob")
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userName", ignore = true)
    User toUserEntity(UserUpdateDto dto);

    @Mapping(target = "dob", source = "dateOfBirth")
    UserDTO toDTO(User user);

    @Mapping(target = "dob", source = "dateOfBirth")
    UserResponse toUserResponse(User user);

    List<User> toListUserEntity(List<UserDTO> dtos);

    List<UserResponse> toListUserResponse(List<User> list);

}
