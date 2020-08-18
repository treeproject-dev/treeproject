package group1.gentrees;

public class Couple {

	public Person person1;

	public Person person2;

	public Couple(Person p1, Person p2) {

		person1 = p1;

		person2 = p2;

	}

	public Person getFirstPerson() {

		return person1;

	}

	public Person getSecondPerson() {

		return person2;

	}

}
