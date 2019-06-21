package com.psoft.UCDb.rest.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psoft.UCDb.rest.model.User;
import com.psoft.UCDb.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/v1/auth")
public class LoginController {
    
    private final String TOKEN_KEY = "banana";
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody User user) throws ServletException {

        User authUser = userService.findByEmail(user.getEmail());
        
        if(authUser == null) {
            throw new ServletException("Usuario nao encontrado!");
        }
        
        if(!authUser.getPassword().equals(user.getPassword())) {
            throw new ServletException("Senha invalida!");
        }
        
        String token = Jwts.builder().
                setSubject(authUser.getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .compact();
        
        return new LoginResponse(token);
        
    }
    
    private class LoginResponse {
        public String token;
        
        public LoginResponse(String token) {
            this.token = token;
        }
    }
    
}
