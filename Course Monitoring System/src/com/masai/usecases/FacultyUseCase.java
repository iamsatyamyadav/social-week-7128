package com.masai.usecases;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masai.bean.Faculty;
import com.masai.dao.FacultyDao;
import com.masai.dao.FacultyDaoImp;
import com.masai.exceptions.FacultyException;
import com.masai.run.Options;

public class FacultyUseCase {

	
	public static void facultyLogin() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter faculty details -");
		System.out.println("Enter Username: ");
		String username = sc.next();

		System.out.println("Enter Password: ");
		String password = sc.next();

		try {
			new FacultyDaoImp().loginFaculty(username, password);

			System.out.println("\nWelcome! Login Successful...");
			Options.facultyCanDo();

		} catch (FacultyException e) {

			System.out.println(e.getMessage());
			System.out.println("\nTry again...");
			Options.selectUser();
		}

	}


	public static void facultyRegister() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter faculty details - ");

		System.out.println("Enter faculty name:");
		String name = sc.next();

		System.out.println("Enter faculty address:");
		String address = sc.next();

		System.out.println("Enter faculty mobile:");
		String mobile = sc.next();

		System.out.println("Enter faculty email:");
		String email = sc.next();

		System.out.println("Enter faculty username:");
		String username = sc.next();

		System.out.println("Enter faculty password:");
		String password = sc.next();

		FacultyDao dao = new FacultyDaoImp();
		Faculty faculty = new Faculty();

		faculty.setFacultyName(name);
		faculty.setFacultyAddress(address);
		faculty.setMobile(mobile);
		faculty.setEmail(email);
		faculty.setUsername(username);
		faculty.setPassword(password);

		try {
			String result = dao.registerFaculty(faculty);
			System.out.println(result);

		} catch (FacultyException e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			Options.facultyOptions();

		}

		Options.facultyOptions();
	}

	public static void facultyUpdateById() {

		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter faculty ID to update - ");

		int id = 0;
		try {

			System.out.println("Enter the faculty id");
			id = sc.nextInt();

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("Id is numeric!");

			System.out.println("\nTry again...");
			Options.facultyOptions();
		}

		System.out.println("Enter new name");
		String name = sc.next();

		System.out.println("Enter new address");
		String address = sc.next();

		System.out.println("Enter new mobile");
		String mobile = sc.next();

		System.out.println("Enter new email");
		String email = sc.next();

		System.out.println("Enter new username");
		String username = sc.next();

		System.out.println("Enter new password");
		String password = sc.next();

		FacultyDao dao = new FacultyDaoImp();
		Faculty faculty = new Faculty();

		faculty.setFacultyName(name);
		faculty.setFacultyAddress(address);
		faculty.setMobile(mobile);
		faculty.setEmail(email);
		faculty.setUsername(username);
		faculty.setPassword(password);

		String result;
		try {
			result = dao.upadteFacultyById(id, faculty);
			System.out.println("\n" + result);

		} catch (FacultyException e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			Options.facultyOptions();

		}

		Options.facultyOptions();

	}

	public static void facultyView() {

		try {

			List<Faculty> faculties = new FacultyDaoImp().viewAllFaculty();

			faculties.forEach(f -> {

				System.out.println("Faculty ID : " + f.getFacultyId());
				System.out.println("Faculty Name : " + f.getFacultyName());
				System.out.println("Faculty Address : " + f.getFacultyAddress());
				System.out.println("Faculty Mobile : " + f.getMobile());
				System.out.println("Faculty Email : " + f.getEmail());
				System.out.println("Faculty Username : " + f.getUsername());
				System.out.println("Faculty Password : " + f.getPassword());

				System.out.println("==================================");
			});

		} catch (FacultyException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			Options.facultyOptions();

		}

		Options.facultyOptions();

	}

}
