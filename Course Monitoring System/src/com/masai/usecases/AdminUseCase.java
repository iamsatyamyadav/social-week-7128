package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.AdminDaoImp;
import com.masai.exceptions.AdminException;
import com.masai.run.Options;

public class AdminUseCase {

	
	public static void adminLogin() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter admin details - ");
		System.out.println("Enter Username: ");
		String username = sc.next();
		
		System.out.println("Enter Password: ");
		String password = sc.next();
		
		try {
			new AdminDaoImp().loginAdmin(username, password);
			Options.adminOptions();
			
		} catch (AdminException e) {
            System.out.println(e.getMessage());			
			
			System.out.println("\nTry again...");
			Options.selectUser();
		}
	}
	
}
