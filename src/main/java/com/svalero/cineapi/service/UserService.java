package com.svalero.cineapi.service;

import com.svalero.cineapi.domain.User;
import com.svalero.cineapi.dto.UserInDto;
import com.svalero.cineapi.dto.UserOutDto;
import com.svalero.cineapi.exception.UserNotFoundException;
import com.svalero.cineapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public UserOutDto saveUser(UserInDto userInDto){
        User user = new User();
        modelMapper.map(userInDto, user);
        user.setRegistrationDate(LocalDate.now());
        User newUser = userRepository.save(user);

        UserOutDto userOutDto = new UserOutDto();
        modelMapper.map(newUser, userOutDto);

        return userOutDto;
    }

    public void removeUser(long userId) throws UserNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.delete(user);
    }

    public void modifyUser (User newUser, long userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User existingUser = user.get();
            existingUser.setUsername(newUser.getUsername());
            existingUser.setPassword(newUser.getPassword());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPhone(newUser.getPhone());
            userRepository.save(existingUser);
        }
    }


}
