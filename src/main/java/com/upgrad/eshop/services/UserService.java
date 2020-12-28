package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;

public interface UserService {
    public User acceptUserDetails(RegisterRequest registerRequest)
            throws UsernameAlreadyRegisteredException, EmailAlreadyRegisteredException;
    public User findByUsername(String username);
    public User findById(Long id) throws UserNotFoundException;
    public User getLoggedInUser();
    public User addUser(RegisterRequest registerRequest);
    public User saveUser(User user);
}
