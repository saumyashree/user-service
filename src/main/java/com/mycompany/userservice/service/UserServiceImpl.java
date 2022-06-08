package com.mycompany.userservice.service;

import com.mycompany.userservice.dto.ErrorDTO;
import com.mycompany.userservice.dto.UserDTO;
import com.mycompany.userservice.entity.UserEntity;
import com.mycompany.userservice.exception.BusinessException;
import com.mycompany.userservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    /**
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO register(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        userEntity = userRepository.save(userEntity);
        BeanUtils.copyProperties(userEntity, userDTO);
        userDTO.setPassword(null);
        return userDTO;
    }

    /**
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO login(UserDTO userDTO) {

        Optional<UserEntity> optUserEntity = userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        optUserEntity.orElseThrow(()-> {
            ErrorDTO errorDTO = new ErrorDTO("AUTH_001", "Invalid Credentials");
            List<ErrorDTO> errorDTOList = new ArrayList<>();
            errorDTOList.add(errorDTO);
            return new BusinessException(errorDTOList);
        });
        BeanUtils.copyProperties(optUserEntity.get(), userDTO);
        userDTO.setPassword(null);
        return userDTO;
    }
}
