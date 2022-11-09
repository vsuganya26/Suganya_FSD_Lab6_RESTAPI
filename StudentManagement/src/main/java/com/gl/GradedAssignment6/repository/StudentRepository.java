package com.gl.GradedAssignment6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.GradedAssignment6.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	List<Student> findByFirstNameContainsAndLastNameContainsAllIgnoreCase(String firstName, String lastName);

}
