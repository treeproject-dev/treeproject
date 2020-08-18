package treeproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Person {
	public String firstName, surName, gender;
	public Date dateBirth, death;
	public Person myFather;
	public Person myMother;		
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public Person() {
		this.firstName = "First Name";
		this.surName = "Surname";

	}

//constructers
	public Person(String firstName, String surName, String gender, Date dateBirth) {
		this.firstName = firstName;
		this.surName = surName;
		this.gender = gender;
		this.dateBirth = dateBirth;

	}

	public Person(String firstName, String surName, String gender,Date dateBirth,Date death, Person father,
			Person mother) {
		this.firstName = firstName;
		this.surName = surName;
		this.gender = gender;
		this.dateBirth = dateBirth;
		this.death = death;
		this.myFather = father;
		this.myMother = mother;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Date getDateBirth() {

		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		

		this.dateBirth = dateBirth;
	}

	public Date getDeath() {
		return death;
	}

	public void setDeath(Date death) {

		this.death = death;
	}

	public Person getMyFather() {
		return myFather;
	}

	public void setMyFather(Person myFather) {
		this.myFather = myFather;
	}

	public Person getMyMother() {
		return myMother;
	}

	public void setMyMother(Person myMother) {
		this.myMother = myMother;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {

		return firstName + " " + surName + " " + gender + " " + simpleDateFormat.format(dateBirth) + " ";
	}
	public static void main(String[]args) throws ParseException {
	Person p11 = new Person("Maria", "Spring", "Female",simpleDateFormat.parse("1993-05-06") );
	String p12 = simpleDateFormat.format(p11.getDateBirth());
	System.out.println(p12);
	System.out.println(p11);
	}
}
