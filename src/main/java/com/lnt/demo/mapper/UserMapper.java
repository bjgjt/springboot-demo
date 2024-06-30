package com.lnt.demo.mapper;

import com.lnt.demo.dto.reponse.UserResponse;
import com.lnt.demo.dto.request.UserCreationRequest;
import com.lnt.demo.dto.request.UserUpdateRequest;
import com.lnt.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    List<UserResponse> toListUserResponses(List<User> listUsers);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
