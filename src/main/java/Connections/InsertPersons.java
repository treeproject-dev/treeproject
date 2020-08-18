package Connections;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import group1.gentrees.Person;

public class InsertPersons {
protected static AppConnect conn = new AppConnect();
	
public boolean insertPerson(Person person) {

    int i = 0;
    try {
      String sql = "INSERT INTO gentrees.persons (person_name, person_surname, person_date, person_date_death, person_gender) VALUES (?, ?,?,?,?)";
      PreparedStatement preparedStatement = conn.conn.prepareStatement(sql);
      preparedStatement.setString(1, person.getFirstName());
      preparedStatement.setString(2, person.getSurName());
      preparedStatement.setDate(3, person.getDateBirth());
      preparedStatement.setDate(4, person.getDeath());
      preparedStatement.setString(5, person.getGender().toString());
      i = preparedStatement.executeUpdate();
      conn.conn.commit();
      
    } catch (SQLException e) {
      //  Auto-generated catch block
      e.printStackTrace();
    }
  
    return true;
  }
	
}
