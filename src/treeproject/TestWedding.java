package treeproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestWedding {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static void displayWedding(Wedding wedding) {

		Person p1 = wedding.getCouple().getFirstPerson();

		Person p2 = wedding.getCouple().getSecondPerson();	
		

		System.out.println(p1.toString()+ " married " +p2.toString());

		System.out.println(simpleDateFormat.format(wedding.getWeddingDate()));


	}

	public static void main(String[] args) throws ParseException {

		Person first = new Person("Valerij", "Java", "Male", simpleDateFormat.parse("2015-05-06"));

		Person second = new Person("Maria", "Spring", "Female", simpleDateFormat.parse("2015-05-06"));

		Couple couple1 = new Couple(first, second);

		Wedding w1 = new Wedding(simpleDateFormat.parse("2019-05-06"), couple1);

		displayWedding(w1);

		Person p2 = new Person("Karlis", "Skarins", "Male", simpleDateFormat.parse("2015-05-06"));

		Person p3 = new Person("Ilze", "Test", "Female",simpleDateFormat.parse("1998-05-06"));

		Couple c2 = new Couple(p2, p3);

		Wedding w2 = new Wedding(simpleDateFormat.parse("2019-10-02"), c2);

		displayWedding(w2);

		Person p4 = new Person("Valerij", "Java", "Male", simpleDateFormat.parse("2015-05-06"));

		Person p5 = new Person("Maria", "Spring", "Female", simpleDateFormat.parse("2015-05-06"));

		Couple c3 = new Couple(p4, p5);

		Wedding w3 = new Wedding(simpleDateFormat.parse("2015-05-06"), c3);
		displayWedding(w3);
				

	}

}
