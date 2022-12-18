package com.masai.dao;

import java.util.List;

import com.masai.bean.Faculty;
import com.masai.exceptions.FacultyException;

public interface FacultyDao {

	public Faculty loginFaculty(String username, String password) throws FacultyException;

	public String registerFaculty(Faculty faculty) throws FacultyException;

	public String upadteFacultyById(int id, Faculty faculty) throws FacultyException;

	public List<Faculty> viewAllFaculty() throws FacultyException;

	
}
