package com.mycompany.userservice.service;

import com.mycompany.userservice.dto.UserDTO;

public interface UserService {

    public UserDTO register(UserDTO userDTO);
    public UserDTO login(UserDTO userDTO);
}
