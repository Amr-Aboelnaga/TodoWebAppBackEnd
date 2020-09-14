package com.rest.webservices.restfulwebservices.jwt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservices.restfulwebservices.signup.User;
import com.rest.webservices.restfulwebservices.todo.Todo;
@Repository
public interface UserJwtJpaRepository extends JpaRepository<JwtUserDetails, String>{
	}
