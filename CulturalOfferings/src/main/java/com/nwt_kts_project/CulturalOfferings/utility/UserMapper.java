package com.nwt_kts_project.CulturalOfferings.utility;

import com.nwt_kts_project.CulturalOfferings.dto.UserDTO;
import com.nwt_kts_project.CulturalOfferings.model.User;

public class UserMapper implements MapperInterface <User, UserDTO>{


    @Override
    public User toEntity(UserDTO dto) {
        return new User(dto.getEmail(),dto.getUsername(),dto.getPassword());
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getEmail(),entity.getUsername());
    }
}
