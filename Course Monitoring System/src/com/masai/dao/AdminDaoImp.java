package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.masai.bean.Admin;
import com.masai.exceptions.AdminException;
import com.masai.utility.DBUtill;

public class AdminDaoImp implements AdminDao{

	@Override
	public Admin loginAdmin(String username, String password) throws AdminException {
		Admin admin = null;
		
		try(Connection cn =  DBUtill.provideConnection()) {
			
			PreparedStatement ps = cn.prepareStatement("Select * from admin where username = ? and password = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String user = rs.getString("username");
				String pass = rs.getString("password");
				
				admin = new Admin(user, pass);
			}else
				throw new AdminException("Invalid username & password");
			
		} catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}
		
		return admin;
	}

}
