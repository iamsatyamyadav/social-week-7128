package com.masai.usecases;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masai.bean.Batch;
import com.masai.dao.BatchDao;
import com.masai.dao.BatchDaoImp;
import com.masai.exceptions.BatchException;
import com.masai.run.Options;

public class BatchUseCase {

	
	public static void createBatch() {

		BatchDao dao = new BatchDaoImp();
		Batch batch = new Batch();

		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter batch details - ");

		try {

			int id = (Integer) null;
			try {

				System.out.println("Enter batch id");
				id = sc.nextInt();

				boolean res = dao.getBatchByBatchId(id);

				if (res) {
					System.out.println("\nThis is already exists!");

					System.out.println("\nTry again...");
					Options.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			int courseId = 0;
			try {

				System.out.println("Enter course Id");
				courseId = sc.nextInt();

				boolean res = dao.getCourseByCourseId(courseId);

				if (res == false) {
					System.out.println("\nThis course Id doesn't exists!");

					System.out.println("\nTry again...");
					Options.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			int facultyId = 0;
			try {

				System.out.println("Enter faculty Id");
				facultyId = sc.nextInt();

				boolean res = dao.getFacultyByFacultyId(facultyId);

				if (res == false) {
					System.out.println("\nThis faculty Id doesn't exists!");

					System.out.println("\nTry again...");
					Options.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter no of students");
			int numberOfStudents = sc.nextInt();

			System.out.println("Enter batch start-date (yyyy-mm-dd)");
			String batchStartDate = sc.next();

			System.out.println("Enter duration of the batch");
			String duration = sc.next();

			batch.setBatchId(id);
			batch.setCourseId(courseId);
			batch.setFacultyId(facultyId);
			batch.setNumberOfStudents(numberOfStudents);
			batch.setBatchStartDate(batchStartDate);
			batch.setDuration(duration);

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nInvalid data-type!");

			System.out.println("\nTry again...");
			Options.batchOptions();
		}

		try {
			String result = dao.createBatch(batch);
			System.out.println("\n" + result);

		} catch (BatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\n" + e.getMessage());

			System.out.println("\nTry again...");
			Options.batchOptions();

		}

		Options.batchOptions();

	}

	

	public static void viewAllBatch() {

		try {

			List<Batch> batches = new BatchDaoImp().getAllBatchDetails();

			batches.forEach(b -> {

				System.out.println("Batch ID : " + b.getBatchId());
				System.out.println("Allocate Course ID : " + b.getCourseId());
				System.out.println("Allocate Faculty ID : " + b.getFacultyId());
				System.out.println("Batch Having no of Student: " + b.getNumberOfStudents());
				System.out.println("Batch Start Date : " + b.getBatchStartDate());
				System.out.println("Batch Duration : " + b.getDuration());

				System.out.println("==================================");
			});

		} catch (BatchException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			Options.batchOptions();

		}

		Options.batchOptions();

	}

}
