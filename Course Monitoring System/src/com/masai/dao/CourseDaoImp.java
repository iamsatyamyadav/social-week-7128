package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.Course;
import com.masai.exceptions.CourseException;
import com.masai.utility.DBUtill;

public class CourseDaoImp implements CourseDao {

	@Override
	public boolean getCourseByCouseName(String name) throws CourseException {
		
		boolean result = false;
		
		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from course where courseName=?");

			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public String createCourse(Course course) throws CourseException {
		String message = "Failed";
		
		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("insert into course(courseName,fee,courseDescription) values(?,?,?)");

			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getFee());
			ps.setString(3, course.getCourseDescription());

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Course created successfully!";
			}

		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}

		
		return message;
	}

	@Override
	public String upadteCourseByCourseName(String old_name, Course course) throws CourseException {
		
		String message = "Failed";
		
		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("update course set courseName=?,fee=?,courseDescription=? where courseName=?");

			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getFee());
			ps.setString(3, course.getCourseDescription());
			ps.setString(4, old_name);

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Course update successfully!";
			}

		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}
		
		return message;
		
	}

	@Override
	public List<Course> getAllCourseDetails() throws CourseException {
		
		
		List<Course> courses = new ArrayList<Course>();

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from course");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("courseId");
				String name = rs.getString("courseName");
				int fee = rs.getInt("fee");
				String description = rs.getString("courseDescription");

				Course course = new Course();

				course.setCourseId(id);
				course.setCourseName(name);
				course.setFee(fee);
				course.setCourseDescription(description);

				courses.add(course);
			}

		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}

		if (courses.size() == 0)
			throw new CourseException("Empty!");

		return courses;

	

	}

}
