package com.gl.GradedAssignment6.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.GradedAssignment6.entity.Student;
import com.gl.GradedAssignment6.service.StudentService;


@Controller
@RequestMapping ("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		
		List<Student> theStudents = studentService.findAll();
		
		theModel.addAttribute("Student", theStudents);
		
		return "list-Students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Student theStudent = new Student();

		theModel.addAttribute("Student", theStudent);

		return "Students-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
			Model theModel) {

		Student theStudent = studentService.findById(theId);


		theModel.addAttribute("Student", theStudent);

		return "Students-form";			
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("department") String department,@RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if(id!=0)
		{
			theStudent=studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		}
		else
			theStudent=new Student(firstName, lastName, department,country);
			studentService.save(theStudent);


		return "redirect:/student/list";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, Model theModel) {
		if (firstName.trim().isEmpty() && lastName.trim().isEmpty()) {
			return "redirect:/students/list";
		} else {
			List<Student> theStudents = studentService.searchBy(firstName, lastName);
			theModel.addAttribute("Students", theStudents);
			return "list-Students";
		}
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {

		// delete the Student Record
		studentService.deleteById(theId);

		// redirect to /student/list
		return "redirect:/student/list";

	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}
