package com.masai.run;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.masai.usecases.AdminUseCase;
import com.masai.usecases.BatchUseCase;
import com.masai.usecases.CoursePlanUseCase;
import com.masai.usecases.CourseUseCase;
import com.masai.usecases.FacultyUseCase;

public class Options {

	
	
	
		
		@SuppressWarnings("resource")
		public static void selectUser() {

			
			Scanner sc = new Scanner(System.in);		
			System.out
					.println("\nChoose an options - \n" + "1. Admin Login\n" + "2. Faculty Login\n" + "3. Exit ");

			System.out.println("\nEnter any number from above: ");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {

				System.out.println("Invalid input!");

				System.out.println();
				System.out.println("Try again...");

				Options.selectUser();
			}

			switch (choice) {
			case 1:
				AdminUseCase.adminLogin();
				break;
			case 2:
				FacultyUseCase.facultyLogin();
				break;
			case 3:
				System.out.println("Thank you!");
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println();

				System.out.println("Try again...");
				Options.selectUser();
			}
		}

		@SuppressWarnings("resource")
		public static void adminOptions() {

			System.out.println("\nChoose an options - ");
			System.out.println("1. Course (Create,Update,View)\r\n" + "2. Batch (Create,Update,View)\r\n"
					+ "3. Faculty (Create,Update,View)\r\n" + "4. Course-Plan (Create, Update, and View)\r\n"
					+ "5. Exit (Admin Logout)");

			System.out.println("\nEnter any number from above");

			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			switch (choice) {
			case 1: {
				Options.courseOptions();
				break;
			}
			case 2: {
				Options.batchOptions();
				break;
			}
			case 3: {
				Options.facultyOptions();
				break;
			}
			case 4: {
				Options.coursePlanOptions();
				break;
			}
			case 5: {
				break;
			}
			default:
				System.out.println("Invalid choice!");
				System.out.println();

				System.out.println("Try again...");
				Options.adminOptions();
			}

		}

		@SuppressWarnings("resource")
		public static void courseOptions() {

			System.out.println("\nCreate, Update, View Course");
			System.out.println("----------------------------------");

			System.out.println("\nChoose an options - \r\n" + "1. Create Course\r\n" + "2. Update Course by Name\r\n"
					+ "3. View All Courses\r\n" + "5. Exit (Get Admin Options)");

			Scanner sc = new Scanner(System.in);

			System.out.println("\nEnter any number from above:");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {

				System.out.println("Invalid input!");

				System.out.println();
				System.out.println("Try again...");

				Options.courseOptions();
			}

			switch (choice) {
			case 1:
				CourseUseCase.courseCreate();
				break;
			case 2:
				CourseUseCase.courseUpdateByName();
				break;
			case 3:
				CourseUseCase.courseViewAll();
				break;
			case 4:
				Options.adminOptions();
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println();

				System.out.println("Try again...");
				Options.courseOptions();
			}

		}

		@SuppressWarnings("resource")
		public static void batchOptions() {

			System.out.println("\nCreate, Update and View Batch");
			System.out.println("----------------------------------");

			System.out.println("\nChoose an options - \r\n" + "2. View All Batch\r\n" + "3. Exit (Get Admin Options)");

			Scanner sc = new Scanner(System.in);

			System.out.println();
			System.out.println("Enter any number from above:");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {

				System.out.println("Invalid input!");

				System.out.println();
				System.out.println("Try again...");

				Options.batchOptions();
			}

			switch (choice) {
			case 1:
				BatchUseCase.createBatch();
				break;
			case 2:
				BatchUseCase.viewAllBatch();
				break;
			case 3:
				Options.adminOptions();
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println();

				System.out.println("Try again...");
				Options.facultyOptions();
			}

		}

		@SuppressWarnings("resource")
		public static void facultyCanDo() {

			System.out.println(
					"\nChoose an options - \r\n" + "1. View the Course Plan\r\n" + "2. Fill up the Course Planner\r\n"
							+ "3. Update your password\r\n" + "4. Exit (Faculty Logout)");

			Scanner sc = new Scanner(System.in);
			
			System.out.println("\nEnter any number from above:");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {

				System.out.println("\nInvalid input!");
				System.out.println("\nTry again...");

				Options.facultyCanDo();
			}

			switch (choice) {
			case 1:
				FacultyUseCase.facultyUpdateById();
				break;
			case 2:
				FacultyUseCase.facultyView();
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println();

				System.out.println("Try again...");
				Options.facultyOptions();
			}

		}

		@SuppressWarnings("resource")
		public static void facultyOptions() {

			System.out.println("\nCreate, Update, View Faculty");
			System.out.println("----------------------------------");

			System.out.println("\nChoose an options - \r\n" + "1. Create Faculty\r\n" + "2. Update Faculty by ID\r\n"
					+ "3. View All Faculty\r\n" + "4. Exit (Get Admin Options)");

			Scanner sc = new Scanner(System.in);

			System.out.println();
			System.out.println("Enter any number from above:");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {

				System.out.println("Invalid input!");

				System.out.println();
				System.out.println("Try again...");

				Options.facultyOptions();
			}

			switch (choice) {
			case 1:
				FacultyUseCase.facultyRegister();
				break;
			case 2:
				FacultyUseCase.facultyUpdateById();
				break;
			case 3:
				FacultyUseCase.facultyView();
				break;
			case 4:
				Options.adminOptions();
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println();

				System.out.println("Try again...");
				Options.facultyOptions();
			}
		}

		@SuppressWarnings("resource")
		public static void coursePlanOptions() {

			System.out.println("\nCreate, Update, View Course Plan");
			System.out.println("----------------------------------");

			System.out.println("\nChoose an options - \r\n" + "1. Create Course Plan\r\n"
					+ "2. Update Course Plan by ID\r\n" + "3. View All Course Plan\r\n" 
					+ "4. Exit (Get Admin Options)");

			Scanner sc = new Scanner(System.in);

			System.out.println();
			System.out.println("Enter any number from above:");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {

				System.out.println("Invalid input!");

				System.out.println();
				System.out.println("Try again...");

				Options.coursePlanOptions();
			}

			switch (choice) {
			case 1:
				CoursePlanUseCase.createCoursePlan();
				break;
			case 2:
				CoursePlanUseCase.coursePlanUpdateById();
				break;
			case 3:
				CoursePlanUseCase.viewAllCoursePlans();
				break;
			case 4:
				Options.adminOptions();
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println();

				System.out.println("Try again...");
				Options.coursePlanOptions();
			}

		}
}
