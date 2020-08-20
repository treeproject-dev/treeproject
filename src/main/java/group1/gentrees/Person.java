package group1.gentrees;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

public class Person {
	public String firstName, surName;
	public Date dateBirth, death;
	public Person myFather;
	public Person myMother;
	public String gender;
	public int age;


//constructers	
	public Person() {
		this.firstName = "First Name";
		this.surName = "Surname";
        this.gender = "unknown";
	}

	
	public Person(String firstName, String surName, String gender, Date dateBirth) {
		this.firstName = firstName;
		this.surName = surName;
		this.gender = gender;
		this.dateBirth = dateBirth;

	}

	public Person(String firstName, String surName, String gender, Date dateBirth, Date death, Person father,
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

	
	
	
	
	
	
	
	/* ARS */
	

	
	
	// TODO
	//format "yyyy/mm/dd" or "dd.mm.yyyy" or smth else.
	public String getDateBirthString(String format) {
		
		String res = "";
		
		int dd   = this.birth.getDayOfMonth();
		int mm   = this.birth.getMonthValue();
		int yyyy = this.birth.getYear();
		
		switch (format) {
		case "yyyy/mm/dd":
			res = yyyy+"/"+mm+"/"+dd;
		break;
		case "dd.mm.yyyy":
			res = dd+"."+mm+"."+yyyy; 
		break;
		default:
			res = "";
		}
		return res;
	}

	// TODO read BirthDate from String
	//format "yyyy/mm/dd"
	public void setBirthDateFromString(String date, String format) {
		/* EMPTY*/
	}
	
	// TODO the same date convertions for Date of death. 
	
	
	
	// TODO fix dates in "toPersonFrame" method.
	// DEMO VARIANT: FOR ONE PERSON, WITH FIXED FRAME PARAMETERS.
	public String toPersonFrame(int index) {
	  
	  String self = "person"+index;
	  String gen; 

	  //translate gender					<-- perhaps, separate function.
	  switch(this.gender) {
	  case "male":
		  gen = "M";
	  break;
	  case "female":
		  gen = "F";
	  break;
	  default:
		  gen = "U";
	  }
	  
	  //Create Empty Person;
      String str = "var "+self+" = new PersonFrame();\n";
      
	  //Set Person Parameters;      
      str+= self +".setPerson(\""+this.firstName 		    + "\",\""
                                 +this.surName     		    + "\",\""
	                             +     gen         		    + "\",\""
	                             +this.dateBirth.toString() + "\",\""
	                             +this.death.toLocalDate()  + "\");\n";

      //Set Frame Parameters;
      str+= self + ".setFrame(50,50,false,false,false);\n";    
      
	return str;
	} 

	
	//TEST:
	public static void main(String[] args) {
		Person pers = new Person();
		pers.firstName = "Alexander";
		pers.surName   = "Pushkin";
		pers.gender    = "male";
		pers.dateBirth =  new Date(1799,05,26);
		pers.death     =  new Date(1837,01,29);

//		pers.birth         =LocalDate()  
//				new LocalDate(1799,05,26);
//		pers.dateDeath     =  new LocalDate(1837,01,29);

		System.out.println(pers.toString());
		
		System.out.println("PersonFrameConstructor:");
		
		System.out.println(pers.toPersonFrame(1));
		
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

		return firstName + " " + surName + " " + gender + " " + dateBirth + " ";
	}

}
