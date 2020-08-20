package Tests;

import java.applet.Applet;
import java.awt.HeadlessException;

public class TestApplet extends Applet {

	public int x;
	public String str;

	public TestApplet(int x, String str) throws HeadlessException {
		super();
		this.x = x;
		this.str = str;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
    public static void main(String[] args) {
    	TestApplet ta = new TestApplet(12345,"abcde");
	}	
	
}
