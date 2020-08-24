package tree;

import java.util.List;

public interface Haul<Ti,To> {
	
	/***
	 * This Interface is implemented for using lambda-functions.
	 * They should be Kleislei-arrows and have signature:
	 * 
	 * 		k :: x -> [y]
	 * 
	 * It's essential for correct working.
	 * 
	 * as Ti (input type) and To (output type) we understand 
	 * classes Person and Wedding.
	 * 
	 * @param in
	 * @return [out]
	 */

	//kleislei k :: Ti -> To
	public List<To> run(Ti in);
	
}
