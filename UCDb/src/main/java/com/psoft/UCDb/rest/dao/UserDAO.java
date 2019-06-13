package com.psoft.UCDb.rest.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psoft.UCDb.rest.model.User;

@Repository
public interface UserDAO<T, ID extends Serializable> extends JpaRepository<User, Long> {

   User save(User usuario);
   @Query(value="Select u from User u where u.email=:plogin")
   User findByEmail(@Param("plogin")String email);
   
   User deleteByEmail(String email);
}
