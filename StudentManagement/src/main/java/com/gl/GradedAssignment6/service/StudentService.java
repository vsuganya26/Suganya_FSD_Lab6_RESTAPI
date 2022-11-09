package com.gl.GradedAssignment6.service;

import java.util.List;

import com.gl.GradedAssignment6.entity.Student;

public interface StudentService {
	
	public List<Student> findAll();

	public Student findById(int theId);

	public void save(Student thestudent);

	public void deleteById(int theId);
	
	public List<Student> searchBy(String firstName, String lastName);

}
