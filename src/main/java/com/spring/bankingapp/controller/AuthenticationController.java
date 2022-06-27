package com.spring.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bankingapp.configuration.JwtTokenUtil;
import com.spring.bankingapp.model.AuthenticationRequest;
import com.spring.bankingapp.model.AuthenticationResponse;
import com.spring.bankingapp.service.CustomUserDetailsService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Autowired
    public CustomUserDetailsService customUserDetailsService ;
    
    @Autowired
    public JwtTokenUtil jwtTokenUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> generateAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest ) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch(BadCredentialsException e) {
			throw new Exception("Incorrect username/password", e);
		}
		final UserDetails user = customUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String jwtToken = jwtTokenUtil.generateToken(user);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
}
