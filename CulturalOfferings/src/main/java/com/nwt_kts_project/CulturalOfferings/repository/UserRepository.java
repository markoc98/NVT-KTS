package com.nwt_kts_project.CulturalOfferings.repository;

import com.nwt_kts_project.CulturalOfferings.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByEmailIgnoreCase(String email);
    User findByUsername(String username);
}
