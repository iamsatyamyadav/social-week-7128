package com.masai.dao;

import com.masai.bean.Admin;
import com.masai.exceptions.AdminException;

public interface AdminDao {

	public Admin loginAdmin(String username, String password) throws AdminException;
}
