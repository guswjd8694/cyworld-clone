package com.cyworld.cyworld_clone.service;


import com.cyworld.cyworld_clone.dao.UserDao;
import com.cyworld.cyworld_clone.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void registerUser(UserDto userDto) {

        System.out.println("잘 호출되나요");

        userDao.save(userDto);
    }

    public boolean isUsernameAvailable(String username) {
        return userDao.findByUsername(username) == null;
    }
}
