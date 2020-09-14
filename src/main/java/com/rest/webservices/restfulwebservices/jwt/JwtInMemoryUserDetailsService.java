package com.rest.webservices.restfulwebservices.jwt;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest.webservices.restfulwebservices.signup.User;


@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  public static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
	@Autowired
	public UserJwtJpaRepository userJwtJpaRepository;
  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
        "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(1L, "admin",
            "$2a$10$5s4cDhLyyP0T0JRe.QKDXe3WRC.q/.LbgHrBlbpr2S0iT9Dt52joG", "ROLE_USER_2"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
//        .filter(user -> user.getUsername().equals(username)).findFirst();
//
//    if (!findFirst.isPresent()) {
//      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//    }
//    return findFirst.get();
	  
	Optional<JwtUserDetails> user=userJwtJpaRepository.findById(username);
	if(!user.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));

	}
    return user.get();
  }


}


