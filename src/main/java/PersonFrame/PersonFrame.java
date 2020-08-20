package PersonFrame;

/*
 * 	This Class = Container for Person class with methods of interaction between Java & JavaScript.
 * 
 * 			possibly, it can be splitted into interface & its realization.
 * 			perhaps, Canvas-interface.
 * 
 */
import group1.gentrees.Person;

public class PersonFrame {
	
	public Person self;	
	
	/* technical: */
	
	int pos_x;
	int pos_y;
	
	//Frame_Size:
	public static int width;
	public static int height;
	public static int indentLeft;
	public static int indentTop;		
	public static int stringThreshold;	// <- distance between strings;
	
	//state:
	public boolean activated = false;
	// perhaps: public boolean shown = true; 

	public PersonFrame(Person self, int pos_x, int pos_y, boolean activated) {
		super();
		this.self = self;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.activated = activated;
	} 
	
	/* INTERFACE OF INTERACTION WITH JAVA-SCRIPT */
	
	// PersonFrame draws itself.
	public void draw() {
		// call:
			// 
		
	}
	
	// Check, if (i,j) coord are internal for this frame;
	public boolean clickInside(int i, int j) {
		boolean res = false;
		if ( pos_x < i && i < pos_x + width )
			if ( pos_y < j && j < pos_y+height )
				res = true;
		return res;
	}
	
	
	public void onClick() {
		
	}


	
	

}
