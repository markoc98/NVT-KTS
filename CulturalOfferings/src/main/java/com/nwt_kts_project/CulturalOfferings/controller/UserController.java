package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.dto.UserDTO;
import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.VerificationTokenRepository;
import com.nwt_kts_project.CulturalOfferings.service.CulturalOfferingService;
import com.nwt_kts_project.CulturalOfferings.service.EmailSenderService;
import com.nwt_kts_project.CulturalOfferings.service.UserService;
import com.nwt_kts_project.CulturalOfferings.utility.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CulturalOfferingService culturalOfferingService;

    private UserMapper userMapper;

    public UserController() {

        userMapper = new UserMapper();
    }

    //GET ZAHTEV ZA DOBAVLJANJE JEDNOG USERA PO ID-u
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){

        User user = userService.findOne(id);


        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    //GET ZAHTEV ZA DOBAVLJANJE SVIH USERA
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();

        return new ResponseEntity<>(toUserDTOList(users), HttpStatus.OK);
    }

    private List<UserDTO> toUserDTOList(List<User> users){
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user: users) {
            userDTOS.add(userMapper.toDto(user));
        }
        return userDTOS;
    }

    //POST ZAHTEV KREIRANJE NOVOG USERA
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO){
        User user;
        try {
            user = userService.create(userMapper.toEntity(userDTO));
            user.setEnabled(true);
            userService.update(user,user.getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
    }

    ///PUT ZAHTEV UPDATE POSTOJECEG USERA
    @PreAuthorize("hasRole('ROLE_ADMIN')" + "|| hasRole('ROLE_USER')")
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable Long id){
        User user;
        try {
            user = userService.update(userMapper.toEntity(userDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto1(user), HttpStatus.OK);
    }

    //DELETE ZAHTEV BRISANEJ POSTOJECEG USERA
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
            userService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE ZAHTEV BRISANEJ POSTOJECEG USERA
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/subscribe/{culturalOfferingId}/{userId}", method=RequestMethod.PUT)
    public ResponseEntity<Void> subscribeToNewsletter(@PathVariable Long culturalOfferingId, @PathVariable Long userId){
        try {
            System.out.println("===========================================" + culturalOfferingId+ userId);

            CulturalOffering co = culturalOfferingService.findOne(culturalOfferingId);
            User user = userService.findOne(userId);

            Set<CulturalOffering> subscribedTo = user.getSubscribedTo();
            Set<User> subscribers = co.getSubscribedUsers();

            subscribedTo.add(co);
            subscribers.add(user);
            userService.update(user, user.getId());
            culturalOfferingService.update(co,co.getId());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
