package com.masai.dao;

import java.util.List;

import com.masai.bean.Batch;
import com.masai.exceptions.BatchException;

public interface BatchDao {

	public boolean getCourseByCourseId(int courseId) throws BatchException;

	public boolean getFacultyByFacultyId(int facultyId) throws BatchException;

	public boolean getBatchByBatchId(int id) throws BatchException;

	public String createBatch(Batch batch) throws BatchException;

	public List<Batch> getAllBatchDetails() throws BatchException;
	
	
	
}
