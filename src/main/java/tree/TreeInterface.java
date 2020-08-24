package tree;

import java.util.List;

import group1.gentrees.*;

public interface TreeInterface {

    /***
     * 	This Interface is implemented for Monadic Navigation;
     * 
     * 	x$y functions are analogs of bind (>>=) operator from Haskell. 
     *  x_y functions are analogs of forgetful bind (>>) from Haskell.
     *  
     *  Inside Tree we have 2 lists, hence we have 4 implementations
     *  of each bind. Perhaps it would be better to call it Diadic 
     *  navigation. 
     *  
     *  arguments should have signature :: x -> [y] 
     * 
     * @param k :: x -> [y];
     * @return TreeInterface;
     */
	
	/* BINDS */
	
	// (>>=)
	public TreeInterface p$p(Haul<Person,Person> k);
	public TreeInterface p$w(Haul<Person,Wedding> k);
	public TreeInterface w$p(Haul<Wedding,Person> k);
	public TreeInterface w$w(Haul<Wedding,Wedding> k);

	// (>>)
	public TreeInterface p_p(Haul<Person,Person> k);
	public TreeInterface p_w(Haul<Person,Wedding> k);
	public TreeInterface w_p(Haul<Wedding,Person> k);
	public TreeInterface w_w(Haul<Wedding,Wedding> k);
	
	public List<Person> toPersons();
	public List<Wedding> toWeddings();
	public TreeInterface toTree();
	
	/* Containers */
	
	/*
	 * Tree types (not TreeInterfaces) because of we take
	 * methods from other classes that work with real,
	 * not abstract data. It violates abstract design 
	 * but should work.
	 */
	
	// (>>=)
	public TreeInterface addParents();
	public TreeInterface addWeddings(Tree t); 
	public TreeInterface addChildrenFromWeddings(Tree t);		//for Weddings:
	public TreeInterface addSpouses(Tree t);					//for Persons:
	public TreeInterface addSiblings(Tree t);
	public TreeInterface addFamilies(Tree t);
	
	// (>>)
	public TreeInterface gainParents();  
	public TreeInterface gainWeddings(Tree t);  
	public TreeInterface gainChildrenFromWeddings(Tree t);		//for Weddings:
	public TreeInterface gainSpouses(Tree t);					//for Persons:
	public TreeInterface gainSiblings(Tree t);  
	public TreeInterface gainFamilies(Tree t);
	
	// load from data base
	public TreeInterface loadFamilies();
	public TreeInterface loadSpouses();  
	public TreeInterface loadWeddings();
	public TreeInterface loadChildren();

	public String toJSON();
			
}
