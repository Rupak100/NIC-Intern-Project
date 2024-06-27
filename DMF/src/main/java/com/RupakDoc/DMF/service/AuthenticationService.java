package com.RupakDoc.DMF.service;



import com.RupakDoc.DMF.Jwt.JwtService;
import com.RupakDoc.DMF.model.Role;
import com.RupakDoc.DMF.auth.AuthenticationRequest;
import com.RupakDoc.DMF.auth.AuthenticationResponse;
import com.RupakDoc.DMF.auth.RegisterRequest;
import com.RupakDoc.DMF.model.UserEntity;
import com.RupakDoc.DMF.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){

        Date date = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.YEAR, 1);
        Date expiryDate = calender.getTime();

        var user = UserEntity.builder()
                .client_id(request.getClient_id())
                .client_secret(passwordEncoder.encode(request.getClient_secret()))
                .created_on(date)
                .expiry_on(expiryDate)
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getClient_id(), request.getClient_secret())
        );
        var user = userRepository.findByClientId(request.getClient_id()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
