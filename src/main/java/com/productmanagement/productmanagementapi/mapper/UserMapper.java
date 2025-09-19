package com.productmanagement.productmanagementapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.productmanagement.productmanagementapi.model.dto.UserDTO;
import com.productmanagement.productmanagementapi.model.entity.User;
import com.productmanagement.productmanagementapi.model.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // convert from UserDTO to User Entity or Domain Model
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "dateOfBirth", source = "dob")
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userName", ignore = true)
    User toUserEntity(UserDTO dto);

    // Convert User to UserDTO
    @Mapping(target = "dob", source = "dateOfBirth")
    UserDTO toDTO(User user);

    List<User> toListUserEntity(List<UserDTO> dtos);

    @Mapping(target = "dob", source = "dateOfBirth")
    UserResponse toUserResponse(User user);

    List<UserResponse> toListUserResponse(List<User> list);

    // Convert list of User to list UserDTO
    // List<UserDTO> toListUserDTO(List<User> list);

}
