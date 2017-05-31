package com.packt.restful.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packt.restful.controller.dao.StudentDao;
import com.packt.restful.model.Student;

@Controller
@RequestMapping("/college")
public class StudentController {
	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping(value = "/students/{roleNumber}", method = RequestMethod.GET)
	public @ResponseBody
	Student retrieve(@PathVariable String roleNumber) {
		return studentDao.retrieve(roleNumber);
	}

	@RequestMapping(value = "/students/", method = RequestMethod.GET)
	public @ResponseBody List<Student> retrieveAll() {
		return new ArrayList<Student>(studentDao.retrieveAll());
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
}
