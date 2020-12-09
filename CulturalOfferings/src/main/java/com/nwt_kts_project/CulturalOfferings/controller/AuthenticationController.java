package com.nwt_kts_project.CulturalOfferings.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nwt_kts_project.CulturalOfferings.model.VerificationToken;
import com.nwt_kts_project.CulturalOfferings.repository.VerificationTokenRepository;
import com.nwt_kts_project.CulturalOfferings.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.nwt_kts_project.CulturalOfferings.dto.UserDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserLoginDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserTokenStateDTO;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.repository.UserRepository;
import com.nwt_kts_project.CulturalOfferings.security.TokenUtils;
import com.nwt_kts_project.CulturalOfferings.service.CustomUserDetailsService;
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

	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private TokenUtils tokenUtils;
	@Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
	
	//endpoint za login
	@PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
                                                                    HttpServletResponse response) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
    }
	
	// endpoint koji se poziva da se token osvezi, u slucaju usteka vazenja JWT tokena
    @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        User user = (User) this.userDetailsService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenStateDTO(refreshedToken, expiresIn));
        } else {
            UserTokenStateDTO userTokenState = new UserTokenStateDTO();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }
	
	 // Endpoint za registraciju novog korisnika
    @PostMapping("/sign-up")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userRequest) throws Exception {
        User existUser = userRepo.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new Exception("Username already exists");
        }

        try {
            existUser = userService.create(userMapper.toEntity(userRequest));

            VerificationToken verificationToken = new VerificationToken(existUser);
            verificationTokenRepository.save(verificationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existUser.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("culturalofferings@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/api/auth/confirm-account?token="+verificationToken.getToken());

            emailSenderService.sendMail(mailMessage);

        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        System.out.println(existUser.getPassword()+ "----------------------------");
        
        return new ResponseEntity<>(userMapper.toDto(existUser), HttpStatus.CREATED);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String verificationToken)
    {
        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);

        if(token != null)
        {
            System.out.println("Verified!");
            User user = userRepo.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepo.save(user);
        }
        else
        {
            System.out.println("Link broken or invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // get

}
