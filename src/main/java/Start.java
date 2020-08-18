import java.sql.Date;

import Connections.AppConnect;
import Connections.FindById;
import Connections.InsertPersons;
import group1.gentrees.Gender;
import group1.gentrees.Person;

public class Start {

	public static void main(String[] args) {
		AppConnect conn = new AppConnect();
		InsertPersons ins = new InsertPersons();
		FindById find = new FindById();
		Person person = new Person();
		person.setFirstName("Name 2");
		person.setSurName("with genders");
		person.setDateBirth(new Date(1999,1,1));
		person.setDeath(new Date(2000,2,2));
		person.setGender("Female");
		ins.insertPerson(person);
		
		person=find.findById(7);
		System.out.println(person.toString());
	}

}
