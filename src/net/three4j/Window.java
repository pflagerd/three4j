package net.three4j;

import com.pflager.glut;

public class Window extends glut {
	
	public int innerWidth;
	public int innerHeight;
	
	public Window(String title) {
		innerWidth = 789;
		innerHeight = 910;
		// glutInit();
	}

    public static void main(String[] args) {
    	new Window("Test");
    }
    
}

