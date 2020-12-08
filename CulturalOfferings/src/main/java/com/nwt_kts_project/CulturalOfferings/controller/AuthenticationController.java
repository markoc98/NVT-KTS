package com.nwt_kts_project.CulturalOfferings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nwt_kts_project.CulturalOfferings.dto.UserDTO;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.UserRepository;
import com.nwt_kts_project.CulturalOfferings.service.UserService;
import com.nwt_kts_project.CulturalOfferings.utility.UserMapper;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	private UserMapper userMapper = new UserMapper();
	
	
	
	 // Endpoint za registraciju novog korisnika
    @PostMapping("/sign-up")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userRequest) throws Exception {
    	System.out.println("usao u registraciju");
        User existUser = userRepo.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new Exception("Username already exists");
        }

        try {
            existUser = userService.create(userMapper.toEntity(userRequest));
            //System.out.println(existUser.toString());
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        System.out.println(existUser.getPassword()+ "----------------------------");
        
        return new ResponseEntity<>(userMapper.toDto(existUser), HttpStatus.CREATED);
    }

}
