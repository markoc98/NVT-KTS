package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements ServiceInterface<User> {

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public User create(User entity) throws Exception {
        return null;
    }

    @Override
    public User update(User entity, Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
