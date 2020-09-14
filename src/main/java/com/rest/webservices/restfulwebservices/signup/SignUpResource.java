package com.rest.webservices.restfulwebservices.signup;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfulwebservices.jwt.JwtInMemoryUserDetailsService;
import com.rest.webservices.restfulwebservices.jwt.JwtUserDetails;
import com.rest.webservices.restfulwebservices.jwt.UserJwtJpaRepository;


@CrossOrigin(origins="*")
@RestController
public class SignUpResource {
	@Autowired
	public UserJwtJpaRepository userJwtJpaRepository;
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser
			(@RequestBody User user ) {
		Optional<JwtUserDetails> founduser=userJwtJpaRepository.findById(user.getUsername());
		if(founduser.isPresent()) {
	      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", user.getUsername()));

		}else {
			  BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
			  JwtUserDetails  newuser=new JwtUserDetails(1L, user.getUsername(),
				        encoder.encode(user.getPassword()), "ROLE_USER_2");
			  userJwtJpaRepository.save(new JwtUserDetails(1L, user.getUsername(),
				        encoder.encode(user.getPassword()), "ROLE_USER_2"));
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(newuser.getId()).toUri();
			return  ResponseEntity.created(uri).build();
		}
	}
	
	

}
