package group1.gentrees;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.time.LocalDate;

public class TestWedding {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static void displayWedding(Wedding w) {

		Person p1 = w.getCouple().getFirstPerson();

		Person p2 = w.getCouple().getSecondPerson();

		System.out.println(p1.getFirstName() + " " + p1.getSurName() + " " +p1.getGender()

				+ " married " + p2.getFirstName() + " " + p2.getSurName() + " " + p2.getGender());  

		System.out.println(simpleDateFormat.format(w.getWeddingDate()));

	}

	public static void main(String[] args) throws ParseException {

		//Person p10 = new Person("Valerij", "Java", Gender.Male, LocalDate.of(2000, 10, 1));

		//Person p11 = new Person("Maria", "Spring", Gender.Female, LocalDate.of(2002, 1, 2));

		//Couple c1 = new Couple(p10, p11);

		//Wedding w1 = new Wedding(simpleDateFormat.parse("2019-05-06"), c1);

		//displayWedding(w1);

		//Person p20 = new Person("Karlis", "Skarins", Gender.Male, LocalDate.of(1999, 5, 1));

		//Person p21 = new Person("Ilze", "Test", Gender.Female, LocalDate.of(2005, 4, 25));

		//Couple c2 = new Couple(p20, p21);

		//Wedding w2 = new Wedding(simpleDateFormat.parse("2019-10-02"), c2);

		//displayWedding(w2);
		
	

	}

}
