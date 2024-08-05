package com.jvsc.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.jvsc.Request.AccessDto;
import com.jvsc.Request.AuthDto;
import com.jvsc.Response.FitException;
import com.jvsc.security.jwt.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AccessDto login(AuthDto authDto){
        try {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authDto.getName(), authDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(auth);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userDetails);

            var accessDto = new AccessDto(token);

            return accessDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FitException("Credenciais inválidas", HttpStatus.UNAUTHORIZED.value());
        }
    }
}
