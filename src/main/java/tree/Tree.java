package tree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import group1.gentrees.*;

public class Tree implements TreeInterface {

	/* Fields */
	
	public List<Person> persons;
	public List<Wedding> weddings;

	/* Constructors: */
	
	// Trivial constructor
	private Tree() {
		this.persons  = new ArrayList<Person>();
		this.weddings = new ArrayList<Wedding>();
	}
	
	//Constructor from Lists:
	public Tree(List<Person> persons, List<Wedding> weddings) {
		this.persons 	= persons;
		this.weddings 	= weddings;
		//this.digest();
	}
	
	/* Load & Save */
	
	//Constructor from JSON-String;
	Tree(String json) {
	 //smth	
	}
	
	//Translate Object into JSON-String. 
	public String toJSON() {
		return "";
	}
	
	//Constructor from File;
	Tree(File f) {
	 //also smth. Load file and call Tree(json);
	}

	/* Getters: */

	@Override
	public List<Person> toPersons() {
		return persons;
	}

	@Override
	public List<Wedding> toWeddings() {
		return weddings;
	}

	@Override
	public TreeInterface toTree() {
		return this;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void setWeddings(List<Wedding> weddings) {
		this.weddings = weddings;
	}

	/* MISC  */
	
	public Tree clone() {
		Tree x = new Tree();
		x.persons.addAll(this.persons); 
		x.weddings.addAll(this.weddings); 
		return x;
	}
	
				/* ---==[Navigation]==--- */


	//TODO Check them all!!! ( all 8 binds)
	// seems that x_y work fine.
	//			  x$y work fine.

	/****
	 *  x$y are for methods of type :: x -> [y];
	 *  	p for Person;
	 *  	w for Wedding;
	 *  
	 *  these binds represent just composition of map & concat
	 *  functions, that are casted to Set to remove duplicates.
	 *  
	 *  How to use: see Person.getChildren, Person.getSpouses; 
	 *  
	 */
	
	/* (>>=) */

	//[person] -> [person]' ; id [weddings] ;
	public TreeInterface p$p(Haul<Person,Person> k) {
		Tree result = this.clone();
		for (Person p : this.persons)
			result.persons.addAll(k.run(p));	// <- concat . map
		//List -> Set -> List
		result.persons = result.persons.stream().collect(Collectors.toSet())
									   .stream().collect(Collectors.toList());
		return result;
	};

	//[wedding] -> [wedding]' ; id [person] ;
	public TreeInterface w$w(Haul<Wedding,Wedding> k) {
		Tree result = this.clone();
		for (Wedding w : this.weddings)
			result.weddings.addAll(k.run(w));	
		result.weddings = result.weddings.stream().collect(Collectors.toSet())
										 .stream().collect(Collectors.toList());
		return result;
	};
	
	//[person] -> [wedding] ; id [person] ;
	public TreeInterface p$w(Haul<Person,Wedding> k) {
		Tree result = this.clone();
		for (Person p : this.persons)
			result.weddings.addAll(k.run(p));	
		result.weddings = result.weddings.stream().collect(Collectors.toSet())
				   					     .stream().collect(Collectors.toList());
		return result;
	};
	
	//[wedding] -> [person]' ; id [weddings] ;
	public TreeInterface w$p(Haul<Wedding,Person> k) {
		Tree result = this.clone();
		for (Wedding w : this.weddings)
			result.persons.addAll(k.run(w));	
		result.persons = result.persons.stream().collect(Collectors.toSet())
									   .stream().collect(Collectors.toList());
		return result;
	};

	/* (>>) */ 
	// [ The same methods , but they do not collect items. ]
	
	//[person] -> [person]' ; id [weddings] ;
	public TreeInterface p_p(Haul<Person,Person> k) {
		Tree result = new Tree();
			 result.weddings = this.weddings;	
		for (Person p : this.persons)
			result.persons.addAll(k.run(p));	
		result.persons = result.persons.stream().collect(Collectors.toSet())
									   .stream().collect(Collectors.toList());
		return result;
	};
	
	//[wedding] -> [wedding]' ; id [person] ;
	public TreeInterface w_w(Haul<Wedding,Wedding> k) {
		Tree result = new Tree();
			 result.persons = this.persons;		// <- id
		for (Wedding w : this.weddings)
			result.weddings.addAll(k.run(w));	// <- concat . map
		result.persons = result.persons.stream().collect(Collectors.toSet())
				   					   .stream().collect(Collectors.toList());
		return result;
	};
	
	//[person] -> [wedding] ; id [person] ;
	public TreeInterface p_w(Haul<Person,Wedding> k) {
		Tree result = new Tree();
			 result.persons = this.persons;		// <- id
		for (Person p : this.persons)
			result.weddings.addAll(k.run(p));	// <- concat . map
		result.persons = result.persons.stream().collect(Collectors.toSet())
				   					   .stream().collect(Collectors.toList());
		return result;
	};
	
	//[wedding] -> [person]' ; id [weddings] ;
	public TreeInterface w_p(Haul<Wedding,Person> k) {
		Tree result = new Tree();
			 result.weddings = this.weddings;		// <- id
		for (Wedding w : this.weddings)
			result.persons.addAll(k.run(w));	// <- concat . map
		result.persons = result.persons.stream().collect(Collectors.toSet())
									   .stream().collect(Collectors.toList());
		return result;
	};
	
	
	/* Several Containers */
	
	// add = accumulate objects.
	
	@Override
	public TreeInterface addParents() {
		return this.p$p( (p) -> {return p.getParents();} );
	}

	@Override
	public TreeInterface addWeddings(Tree t) {
		return this.p$w( (p) -> {return p.getWeddings(t);} );
	}

	@Override 	//for Weddings:
	public TreeInterface addChildrenFromWeddings(Tree t) {
		return this.w$p( (w) -> {return w.getChildren(t);} );
	}

	@Override	//for Persons:
	public TreeInterface addSpouses(Tree t) {
		return this.p$p( (p) -> {return p.getSpouses(t);} );
	}

	@Override
	public TreeInterface addSiblings(Tree t) {
		return this.p$p( (p) -> {return p.getSiblings(t);} );
	}
	
	@Override
	public TreeInterface addFamilies(Tree t) {
		return this.p$w( p -> { List<Wedding> ws = new ArrayList<Wedding>();
								if (p.getFamily() != null) 
									ws.add(p.getFamily());
								return ws;} );
	}

	// gain = do not accumulate objects.

	@Override
	public TreeInterface gainParents()  {
		return this.p_p( (p) -> {return p.getParents();} );
	}

	@Override
	public TreeInterface gainWeddings(Tree t)  {
		return this.p_w( (p) -> {return p.getWeddings(t);} );
	}
	
	@Override	//for Weddings:
	public TreeInterface gainChildrenFromWeddings(Tree t)  {
		return this.w_p( (w) -> {return w.getChildren(t);} );
	}

	@Override	//for Persons:
	public TreeInterface gainSpouses(Tree t)  {
		return this.p_p( (p) -> {return p.getSpouses(t);} );
	}

	@Override
	public TreeInterface gainSiblings(Tree t)  {
		return this.p_p( (p) -> {return p.getSiblings(t);} );
	}
	
	@Override
	public TreeInterface gainFamilies(Tree t) {
		return this.p_w( p -> { List<Wedding> ws = new ArrayList<Wedding>();
		if (p.getFamily() != null) 
			ws.add(p.getFamily());
		return ws;} );
}


	
	
	// load = get (add/load?) from DataBase ;
	
	/*
	 * findPerson      :: pid -> [p]; <- 1-list
 	 * findMarriage    :: mid -> [w]; <- 1-list
	 */
	
	@Override
	public TreeInterface loadFamilies()  {
		return this.p$w( p -> { return Temporary.findMarriage(p.mid);} );
	}
	
	@Override
	public TreeInterface loadSpouses()  {
		return this.w$p( w -> { List<Person> x = Temporary.findPerson(w.pidH);
								List<Person> y = Temporary.findPerson(w.pidW);
								x.addAll(y);
								return x;} );
	}
	
	@Override
	public TreeInterface loadWeddings()  {
		return this.p$w( p -> { return Temporary
				.SearchWedding( w -> (w.pidH == p.pid
				                  ||  w.pidW == p.pid) );
				} );
	}
	
	@Override
	public TreeInterface loadChildren() {
		return this.w$p( w -> {return Temporary
				.SearchPerson( p -> (p.mid == w.mid));
		} );
	}	
	
	
	/* Methods: */
	
	//Set links between Persons and Weddings:
	// TODO: Test this method.
	public void digest() {
		for (Wedding w : weddings) {
			for (Person p : persons) {
				if (w.mid == p.mid)				
					   p.setFamily(w);
				if (w.pidH == p.pid)
					   w.setHusband(p);
				if (w.pidW == p.pid)
					   w.setWife(p);
			}
		}
	};
	

	/* ~ Interaction of java-Tree and js-Tree objects */

	//Set proper coordinates to Persons;
	public void smartPositions() {
		
	} 
	
					/* Merge Methods */
	
	//Return matching degree of two Trees;
	public static int overlap(Tree t1, Tree t2) {
		return 0;
	}

		
	//public void range {}
	
//	public static void draw {};
	
	
	
	
	
	
	
	
				/* TESTING	*/
	
	
	private static Tree testTree() {
		
		List<Person> ps = new ArrayList<Person>();
		List<Wedding> ws = new ArrayList<Wedding>();
		String g;

		//create persons
		for (int i=0; i<=19; i++) {
			if (i%2==0)      g = "female";
				else 		 g = "male";
			ps.add(new Person(Integer.toString(i),"example",g) );
		}
		
		//create weddings
		Wedding w1 = new Wedding(ps.get(4),ps.get(3));
			ws.add(w1);
			
			System.out.println(ws.get(0).toString());
			
		Wedding w2 = new Wedding(ps.get(6),ps.get(7));
			ws.add(w2);

			System.out.println(ws.get(1).toString());

		Wedding w3 = new Wedding(ps.get(9),ps.get(10));
			ws.add(w3);
			
			System.out.println(ws.get(2).toString());

		Wedding w4 = new Wedding(ps.get(12),ps.get(11));
			ws.add(w4);
			
			System.out.println(ws.get(3).toString());

		Wedding w5 = new Wedding(ps.get(15),ps.get(16));
			ws.add(w5);
			
		Wedding w6 = new Wedding(ps.get(4),ps.get(17));
			ws.add(w6);	

		Wedding w7 = new Wedding(ps.get(0),ps.get(19));
			ws.add(w7);	

		//create families:
		ps.get(0).setFamily(w1);	
		ps.get(1).setFamily(w1);	
		ps.get(2).setFamily(w1);
		
		ps.get(8).setFamily(w2);	
		
		ps.get(3).setFamily(w3);	
		ps.get(5).setFamily(w3);
		ps.get(6).setFamily(w3);	

		ps.get(13).setFamily(w4);	
		ps.get(14).setFamily(w4);	
		
		ps.get(4).setFamily(w5);	
		ps.get(11).setFamily(w5);	

		ps.get(18).setFamily(w6);	

		//
		Tree t = new Tree(ps,ws);
		
		return t;
	}

	public String toConsole() {
		String str = "Persons:\n";
		for (Person p : this.persons) {
			String fam = "";
			if (p.getFamily() != null)
				fam = p.getFamily().toString(); 
			str+= "person: \t" + p.getFirstName() + "\t family: " + fam + "\n";
		}
		str+= "Weddings:\n";
		for (Wedding w : this.weddings) {
			str+= "wedding: \t(" + w.getHusband().getFirstName() + " , " +
					w.getWife().getFirstName() + ")\n";
		}
		
		return str;
	} 
	
	//Tests
	public static void main(String[] args) {
		Test a = () -> {return "Hi\n";};
		System.out.print( a.run() );
		
		Person x = new Person("Charles","Darwin","12.02.1809");
		Person y = x;
			   y.setFirstName("Galton");
		System.out.println(x.toString());
		System.out.println(y.toString());
		

		Tree t = Tree.testTree();
		System.out.print( t.toConsole() );

		Person p0 = t.persons.get(0);
		Person p4 = t.persons.get(4);
			   
		System.out.println(p0.getParents().toString());		// <- ok
		System.out.println("Hi!");
		System.out.println(p4.getChildren(t));				// <- ok 	
		System.out.println(p4.getSpouses(t));				// <- ok	(checked for 2 spouses)
		System.out.println(p4.getWeddings(t));				// <- ok
		System.out.println(p4.getFamily());					// <- ok
		
		Wedding w1 = t.weddings.get(0);
		
		System.out.println(w1.getSpouses());					// <- ok!
		System.out.println(w1.getChildren(t));					// <- ok!
		
		List<Person> ps;
		List<Wedding> ws;
		
		// !! it seems that we need to add Comparable Interface to class Person !!
		
		
		System.out.println("START::");
		
		//getFamily:		
		ps = p4.search().p$p( (p) -> { return p.getParents(); }  )
						.p$p( (p) -> { return p.getChildren(t); }  )
						.p$p( (p) -> { return p.getSpouses(t); }  )
						.toPersons();
	
		//ps = p4.search().addParents().addChildrenFromWeddings(t).addSpouses(t).toPersons();
		//p0.search().gainParents().gainSiblings(t).gainWeddings(t).toWeddings();

		
//		ps = p4.search().addParents().addChildren(t).addSpouses(t).toPersons();

	/*	
		
		System.out.println("test, equality of p4 >>= addParents >>= addChildren"
				+ " >>= addSpouses : "
				+ (ps == p4.search().addParents().addChildren(t).addSpouses(t).toPersons()) );
		
	*/	
		
	//	ps = t.weddings.get(6).search().w$p( (w) -> {return w.getChildren(t);} ).getPersons();
		
		System.out.println("Parents of p0:\t"+ps);
		
		ps = p4.search().addParents().gainWeddings(t).addChildrenFromWeddings(t).toPersons();
		System.out.println("p4:\t"+ps);	

		//p4.search().loadChildren().loadSpouses().loadFamilies().to
		
		ps = p4.search().addParents().gainWeddings(t).addChildrenFromWeddings(t).toPersons();
		System.out.println("ps:\t"+ps);
		
		/*
		Person person = t.persons.get(0);
		
		person.search().p$p( (p) -> {return p.getParents(); }  ) //get parents
					   .p$p( (p) -> {return p.getParents(); }  ) //get grandparents
					   .p$p( (p) -> {return p.getChildren(t);} ) //get uncles,brothers.
					   .getPersons();
	*/	
	}


	
	
	
}
