package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(User user) throws Exception {
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new Exception("User with given email address already exists");
        }
        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new Exception("User with same username already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Long id) throws Exception {
        User existingUser =  userRepository.findById(id).orElse(null);
        if(existingUser == null){
            throw new Exception("User with given id doesn't exist");
        }
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) throws Exception {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser == null){
            throw new Exception("User with given id doesn't exist");
        }
        userRepository.delete(existingUser);
    }
}
