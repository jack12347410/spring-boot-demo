package com.jack.service;

import com.jack.model.Users;
import com.jack.model.UsersDto;
import com.jack.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository _repository;

    public Users FindUser(String userName, String password) {
        return _repository.findByUserNameAndPassword(userName, password);
    }

    public Users Insert(UsersDto user) {
        return _repository.save(user.ConvertToUser());
    }
}
