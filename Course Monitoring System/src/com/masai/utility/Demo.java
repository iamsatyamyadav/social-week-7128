package com.masai.utility;

import java.sql.Connection;

public class Demo {

	public static void main(String[] args) {
		
		Connection cn  = DBUtill.provideConnection();
		
		System.out.println(cn);
		
		System.out.println("Connection established ");
	}
}
