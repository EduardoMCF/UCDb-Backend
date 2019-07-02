package com.psoft.UCDb.rest.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psoft.UCDb.rest.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
	
   User save(User usuario);
   //@Query(value="Select u from Tb_User u where u.email=:plogin")
   User findByEmail(String email);
   
   User deleteByEmail(String email);
}
