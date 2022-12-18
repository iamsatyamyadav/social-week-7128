package com.masai.dao;

import java.util.List;

import com.masai.bean.CoursePlan;
import com.masai.exceptions.CoursePlanException;

public interface coursePlanDao {

	public boolean getBatchByBatchId(int id) throws CoursePlanException;

	public String createCoursePlan(CoursePlan coursePlan) throws CoursePlanException;

	public boolean getPlanByplanId(int id) throws CoursePlanException;

	public String upadteCoursePlanById(int id, CoursePlan coursePlan) throws CoursePlanException;

	public List<CoursePlan> getAllCoursePlanDetails() throws CoursePlanException;

	public List<CoursePlan> pendingCoursePlan() throws CoursePlanException;

	public String updateCoursePlanStatusById(int id) throws CoursePlanException;

}
