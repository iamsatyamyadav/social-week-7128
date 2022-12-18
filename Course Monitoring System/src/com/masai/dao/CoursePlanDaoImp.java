package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.CoursePlan;
import com.masai.exceptions.CoursePlanException;
import com.masai.utility.DBUtill;

public class CoursePlanDaoImp implements CoursePlanDao {

	@Override
	public boolean getBatchByBatchId(int id) throws CoursePlanException {
		boolean result = false;

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement pss = conn.prepareStatement("select * from batch where batchId=?");

			pss.setInt(1, id);
			ResultSet rs = pss.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return result;
	}

	@Override
	public String createCoursePlan(CoursePlan coursePlan) throws CoursePlanException {
		String message = "Failed!";

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("insert into coursePlan(batchId,dayNumber,topic,status) values(?,?,?,?)");

			ps.setInt(1, coursePlan.getBatchId());
			ps.setInt(2, coursePlan.getDayNumber());
			ps.setString(3, coursePlan.getTopic());
			ps.setString(4, coursePlan.getStatus());

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Course plan created successfully!";
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return message;
	}

	@Override
	public boolean getPlanByplanId(int id) throws CoursePlanException {
		boolean result = false;

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement pss = conn.prepareStatement("select * from coursePlan where planId=?");

			pss.setInt(1, id);
			ResultSet rs = pss.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return result;
	}

	@Override
	public String upadteCoursePlanById(int id, CoursePlan coursePlan) throws CoursePlanException {
		String message = "Failed!";

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("update coursePlan set batchId=?,dayNumber=?,topic=?,status=? where planId=?");

			ps.setInt(1, coursePlan.getBatchId());
			ps.setInt(2, coursePlan.getDayNumber());
			ps.setString(3, coursePlan.getTopic());
			ps.setString(4, coursePlan.getStatus());
			ps.setInt(5, id);

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Course plan update successfully!";
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return message;
	}

	@Override
	public List<CoursePlan> getAllCoursePlanDetails() throws CoursePlanException {
		List<CoursePlan> coursePlans = new ArrayList<CoursePlan>();

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from coursePlan");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				CoursePlan coursePlan = new CoursePlan();

				int planId = rs.getInt("planId");
				int batchId = rs.getInt("batchId");
				int dayNumber = rs.getInt("dayNumber");
				String topic = rs.getString("topic");
				String status = rs.getString("status");

				coursePlan.setPlanId(planId);
				coursePlan.setBatchId(batchId);
				coursePlan.setDayNumber(dayNumber);
				coursePlan.setTopic(topic);
				coursePlan.setStatus(status);

				coursePlans.add(coursePlan);
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		if (coursePlans.size() == 0)
			throw new CoursePlanException("Empty!");

		return coursePlans;
	}

	@Override
	public List<CoursePlan> pendingCoursePlan() throws CoursePlanException {
		List<CoursePlan> coursePlans = new ArrayList<CoursePlan>();

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from coursePlan where status='Pending'");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int planId = rs.getInt("planId");

				CoursePlan cp = new CoursePlan();
				cp.setPlanId(planId);

				coursePlans.add(cp);
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		if (coursePlans.size() == 0)
			throw new CoursePlanException("Empty!");

		return coursePlans;
	}

	@Override
	public String updateCoursePlanStatusById(int id) throws CoursePlanException {

		String message = "Failed!";

		try (Connection conn = DBUtill.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("update coursePlan set status='Completed' where planId=?");

			ps.setInt(1, id);
			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Course plan fill-up successfully!";
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());

		}

		return message;
	}

}
