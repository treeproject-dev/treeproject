package treeproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class PersonManager {
	protected Connection conn;

	public PersonManager() {
		try {
		      Class.forName("com.mysql.cj.jdbc.Driver"); // Load the driver class
		      conn = DriverManager.getConnection(
		          "jdbc:mysql://www.999.id.lv/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8", "root",
		          "Student007"); // Create connection
		      conn.setAutoCommit(false);
		    } catch (Exception e) {
		      System.err.println(e);
		    }
	}


		

		public void insertPerson(Person persons) {
		    // #4 Write an sql statement that inserts teacher in database.
		  
		    // 
		    //List<Teacher> teachers = new ArrayList<Teacher>();
		    int i = 0;
		    try {
		      String sql = "INSERT INTO gentrees.persons (person_name, person_surname, person_date, person_date_death, person_gender) VALUES (?, ?,?,?,?)";
		      PreparedStatement preparedStatement = conn.prepareStatement(sql);
		      preparedStatement.setString(1, persons.firstName);
		      preparedStatement.setString(2, persons.surName);
		      preparedStatement.setInt(3, 1993);
		      preparedStatement.setObject(4, " ");
		      preparedStatement.setString(5, "Male");
		      i = preparedStatement.executeUpdate();
		      conn.commit();
		      
		    } catch (SQLException e) {
		      //  Auto-generated catch block
		      e.printStackTrace();
		    }
		
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
	public static void main(String[]args) {
	//	Person tester = new Person("Gena", "Bublik", "Male", LocalDate.of(1999, 3, 3));
		
		
	
		
	}
}
