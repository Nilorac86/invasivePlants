package com.carolin.invasiveplants.Mapper;

import com.carolin.invasiveplants.Entity.Role;
import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.Repository.RoleRepository;
import com.carolin.invasiveplants.RequestDTO.UserRegisterRequestDto;
import com.carolin.invasiveplants.ResponseDTO.UserRegisterResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {

    private final RoleRepository roleRepository;

    public UserRegisterMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public UserRegisterResponseDto toDto(User user){

        if (user == null){
            return null;
        }

        return new UserRegisterResponseDto(
               user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    public User toEntity( UserRegisterRequestDto userDto){

        if (userDto == null){
            return null;
        }

         User user = new User();
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setEmail(userDto.getEmail());
                user.setPassword(userDto.getPassword());
                user.setPhoneNumber(userDto.getPhoneNumber());
                user.setEnable(true);

                // Puts role user when registered.
                Role defaultRole = roleRepository.findByRole("ROLE_USER")
                        .orElseGet(() -> {
                            Role newRole = new Role();
                            newRole.setRoleName("ROLE_USER");
                            return roleRepository.save(newRole);
                        });

                    user.setRole(defaultRole);

                return user;
    }
}
