package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.Batch;
import com.masai.exceptions.BatchException;
import com.masai.utility.DBUtill;

public class BatchDaoImp implements BatchDao {

	@Override
	public boolean getCourseByCourseId(int courseId) throws BatchException {
		
		boolean result = false;
		
		try(Connection cn = DBUtill.provideConnection()) {
			
			PreparedStatement ps  = cn.prepareStatement("select * from course where courseId=?");
			
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
		
		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}
		
		
		return false;
	}

	@Override
	public boolean getFacultyByFacultyId(int facultyId) throws BatchException {
		boolean result = false;
		
		try(Connection cn = DBUtill.provideConnection()) {
			
			PreparedStatement ps  = cn.prepareStatement("select * from faculty where facultyId=?"  );
			
			ps.setInt(1, facultyId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				result = true;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return result;
	}

	@Override
	public boolean getBatchByBatchId(int id) throws BatchException {
		
		boolean result = false;
		
		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from batch where batchName=?");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}
		
		return result;
		
		
	}

	@Override
	public String createBatch(Batch batch) throws BatchException {
		String message = "Failed!";

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"insert into batch(courseId,facultyId,numberOfStudents,batchStartDate,duration) values(?,?,?,?,?)");

			ps.setInt(1, batch.getCourseId());
			ps.setInt(2, batch.getFacultyId());
			ps.setInt(3, batch.getNumberOfStudents());
			ps.setString(4, batch.getBatchStartDate());
			ps.setString(5, batch.getDuration());

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Batch created successfully!";
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		return message;
	}

	@Override
	public List<Batch> getAllBatchDetails() throws BatchException {
		List<Batch> batches = new ArrayList<Batch>();

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from batch");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int bid = rs.getInt("batchId");
				int cid = rs.getInt("courseId");
				int fid = rs.getInt("facultyId");
				int noOfStudents = rs.getInt("numberOfStudents");
				String batchStart = rs.getString("batchStartDate");
				String duration = rs.getString("duration");
				String batchName = rs.getString("batchName");

				Batch batch = new Batch();

				batch.setBatchId(bid);
				batch.setCourseId(cid);
				batch.setFacultyId(fid);
				batch.setNumberOfStudents(noOfStudents);
				batch.setBatchStartDate(batchStart);
				batch.setDuration(duration);
				batches.add(batch);
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		if (batches.size() == 0)
			throw new BatchException("Empty!");

		return batches;
	}

}
