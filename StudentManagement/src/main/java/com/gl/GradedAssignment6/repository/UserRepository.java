package com.gl.GradedAssignment6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gl.GradedAssignment6.entity.User;


public interface UserRepository extends JpaRepository<User, Long>  {
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
    public User getUserByUsername(String username);

}
