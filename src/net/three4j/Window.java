package net.three4j;

import com.pflager.glut;

public class Window extends glut {
	
	public double innerWidth;
	public double innerHeight;
	
	public Window(String title) {
		innerWidth = 600;
		innerWidth = 800;
		// glutInit();
	}

    public static void main(String[] args) {
    	new Window("Test");
    }
    
}

