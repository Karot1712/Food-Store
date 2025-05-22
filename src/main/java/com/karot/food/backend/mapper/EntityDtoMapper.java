package com.karot.food.backend.mapper;

import com.karot.food.backend.DTO.UserDto;
import com.karot.food.backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {
    //User -> DTO
    public UserDto mapUserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole().name());
        return userDto;
    }

}
