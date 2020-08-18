package treeproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PersonManager {
	protected Connection conn;

	public PersonManager() {
		try {
			Class.forName("XXXXXXXX");

			conn = DriverManager.getConnection("XXXXXXX");
			conn.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public boolean insertPerson(String firstName, String surName) {
		// #4 Write an sql statement that inserts teacher in database.
		boolean status = false;

		try {
			String sql = "insert into database_activity.Teacher (firstName,surName) values(?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, surName);
			int insert = preparedStatement.executeUpdate();
			conn.commit();
			if (insert > 0) {
				status = true;
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return status;
	}
	public boolean updatePerson(Person persons) {
		boolean status = false;
		// #6 Write an sql statement that updates teacher information.
		try {
			String sql = "UPDATE database_activity.Teacher SET firstName = ?, surName = ? WHERE id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, persons.getFirstName());
			preparedStatement.setString(2, persons.getSurName());
			//preparedStatement.setString(3, Integer.toString(persons.getId()));
			int update = preparedStatement.executeUpdate();
			conn.commit();
			if (update == 1) {
				status = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return status;

	}
	public boolean deleteTeacher(int id) {
		// #7 Write an sql statement that deletes teacher from database.
		boolean status = false;
		try {
			String sql = "DELETE FROM database_activity.Teacher WHERE (id = ?)";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int delete = preparedStatement.executeUpdate();
			conn.commit();
			if (delete == 1) {
				status = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return status;
	}
	public void closeConnecion() {
		// Close connection to the database server and reset conn object to null
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;
	}
}
