package com.masai.dao;

import java.util.List;

import com.masai.bean.Course;
import com.masai.exceptions.CourseException;

public interface CourseDao {
	
	public boolean getCourseByCouseName(String name) throws CourseException;

	public String createCourse(Course course) throws CourseException;

	public String upadteCourseByCourseName(String old_name, Course course) throws CourseException;

	public List<Course> getAllCourseDetails() throws CourseException;



}
