package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.Authority;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private  AuthorityService authService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(User entity) throws Exception {
        if(userRepository.findByEmail(entity.getEmail()) != null){
            throw new Exception("User with given email address already exists");
        }
        if(userRepository.findByUsername(entity.getUsername()) != null){
            throw new Exception("User with same username already exists");
        }
        User u = new User();
        u.setUsername(entity.getUsername());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        u.setPassword(passwordEncoder.encode(entity.getPassword()));
        u.setEmail(entity.getEmail());
        u.setEnabled(false);
        List<Authority> auth = authService.findByName("ROLE_USER");
        u.setAuthorities(auth);

        u = this.userRepository.save(u);
        
        
        return u;
    }

    @Override
    public User update(User user, Long id) throws Exception {
        User existingUser =  userRepository.findById(id).orElse(null);

        if(existingUser == null){
            throw new Exception("User with given id doesn't exist");
        }
        //existingUser.setPassword(user.getPassword());
        existingUser.setSubscribedTo(user.getSubscribedTo());
        return userRepository.save(existingUser);
    }



    @Override
    public void delete(Long id) throws Exception {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new Exception("User with given id doesn't exist");
        }
        userRepository.delete(existingUser);
    }
}
