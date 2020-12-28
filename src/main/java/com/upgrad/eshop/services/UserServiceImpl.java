package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;
import com.upgrad.eshop.utils.DtoToEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User acceptUserDetails(RegisterRequest registerRequest) throws UsernameAlreadyRegisteredException, EmailAlreadyRegisteredException {
        if (userRepository.findByUserName(registerRequest.getUserName()).isPresent()) {
            throw new UsernameAlreadyRegisteredException("Try any other username, this username is already registered!");
        }
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("Try any other email address, this email address is already registered!");
        }
        return addUser(registerRequest);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with user id " + id + " not found!"));
    }

    @Override
    public User getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(userDetails.getUsername()).orElse(null);
    }

    @Override
    public User addUser(RegisterRequest registerRequest) {
        User user = dtoToEntityConverter.toUser(registerRequest);
        logger.debug("Adding user to database. user [" + user.toString() + "]");
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
