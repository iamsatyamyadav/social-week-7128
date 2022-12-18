package com.masai.usecases;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masai.bean.Batch;
import com.masai.bean.CoursePlan;
import com.masai.dao.BatchDaoImp;
import com.masai.dao.CoursePlanDao;
import com.masai.dao.CoursePlanDaoImp;
import com.masai.exceptions.BatchException;
import com.masai.exceptions.CoursePlanException;
import com.masai.run.Options;

public class CoursePlanUseCase {

	
	public static void createCoursePlan() {

		CoursePlanDao dao = new CoursePlanDaoImp();
		CoursePlan coursePlan = new CoursePlan();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter require course plan details - ");

		System.out.print("\nAvailable Batches\n");

		try {
			List<Batch> batches = new BatchDaoImp().getAllBatchDetails();

			batches.forEach(b -> {
				System.out.print(b.getBatchId() + "(" + b.getBatchId() + ")");
			});

		} catch (BatchException e) {

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			Options.coursePlanOptions();
		}

		try {

			System.out.println("\n\nEnter batch id");
			int batchId = sc.nextInt();

			try {
				boolean res = dao.getBatchByBatchId(batchId);

				if (res == false) {
					System.out.println("\nThis batch id doesn't exists!" + "\nPlease select from above");

					System.out.println("\nTry again...");
					Options.coursePlanOptions();
				}

			} catch (CoursePlanException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter day number of week between(1 for Monday) to (7 for Sunday))");
			int dayNumber = sc.nextInt();

			if ((dayNumber < 1) || (dayNumber > 7)) {

				System.out.println("\nInvalid entry (choose between 1 to 7)");

				System.out.println("\nTry again...");
				Options.coursePlanOptions();
			}

			System.out.println("Enter course plan topic");
			String topic = sc.next();

			// select status for course plan
			String status = "Pending";

			coursePlan.setBatchId(batchId);
			coursePlan.setDayNumber(dayNumber);
			coursePlan.setTopic(topic);
			coursePlan.setStatus(status);

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nInvalid data-type");

			System.out.println("\nTry again...");
			Options.coursePlanOptions();
		}

		try {
			String result = dao.createCoursePlan(coursePlan);
			System.out.println("\n" + result);

		} catch (CoursePlanException e) {
			// TODO Auto-generated catch block

			System.out.println("\n" + e.getMessage());

			System.out.println("\nTry again...");
			Options.coursePlanOptions();

		}

		Options.coursePlanOptions();

	}

	public static void coursePlanUpdateById() {

		CoursePlanDao dao = new CoursePlanDaoImp();
		CoursePlan coursePlan = new CoursePlan();

		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter course plan id to update - ");

		int planId = 0;
		try {

			System.out.println("Enter course-plan id");
			planId = sc.nextInt();

			try {
				boolean res = dao.getBatchByBatchId(planId);

				if (res == false) {
					System.out.println("\nThis planId id doesn't exists!");

					System.out.println("\nTry again...");
					Options.coursePlanOptions();
				}

			} catch (CoursePlanException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter batch id");
			int batchId = sc.nextInt();

			try {
				boolean res = dao.getBatchByBatchId(batchId);

				if (res == false) {
					System.out.println("\nThis batch id doesn't exists!");

					System.out.println("\nTry again...");
					Options.coursePlanOptions();
				}

			} catch (CoursePlanException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter day number of week between(1 for Monday) to (7 for Sunday))");
			int dayNumber = sc.nextInt();

			if ((dayNumber < 1) || (dayNumber > 7)) {

				System.out.println("\nInvalid entry (choose between 1 to 7)");

				System.out.println("\nTry again...");
				Options.coursePlanOptions();
			}

			System.out.println("Enter course plan topic");
			String topic = sc.next();

			String status = "Pending";

			coursePlan.setBatchId(batchId);
			coursePlan.setDayNumber(dayNumber);
			coursePlan.setTopic(topic);
			coursePlan.setStatus(status);

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nInvalid data-type");

			System.out.println("\nTry again...");
			Options.coursePlanOptions();
		}

		String result;
		try {
			result = dao.upadteCoursePlanById(planId, coursePlan);
			System.out.println("\n" + result);

		} catch (CoursePlanException e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			Options.coursePlanOptions();
		}

		Options.coursePlanOptions();

	}

	public static void viewAllCoursePlans() {

		try {

			List<CoursePlan> coursePlans = new CoursePlanDaoImp().getAllCoursePlanDetails();

			coursePlans.forEach(cp -> {

				System.out.println("Course Plan ID : " + cp.getPlanId());
				System.out.println("Course Plan Batch ID : " + cp.getBatchId());

				int day = cp.getDayNumber();

				switch (day) {
				case 1 -> System.out.println("Course Plan Day Number : " + day + "(Monday)");
				case 2 -> System.out.println("Course Plan Day Number : " + day + "(TuesDay)");
				case 3 -> System.out.println("Course Plan Day Number : " + day + "(Wednesday)");
				case 4 -> System.out.println("Course Plan Day Number : " + day + "(Thursday)");
				case 5 -> System.out.println("Course Plan Day Number : " + day + "(Friday)");
				case 6 -> System.out.println("Course Plan Day Number : " + day + "(Satarday)");
				case 7 -> System.out.println("Course Plan Day Number : " + day + "(Sunday)");
				}

				System.out.println("Course Plan Topic : " + cp.getTopic());
				System.out.println("Course Plan Status : " + cp.getStatus());

				System.out.println("==================================");
			});

		} catch (CoursePlanException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			Options.coursePlanOptions();

		}
		Options.coursePlanOptions();

	}

}
