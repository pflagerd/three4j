package net.three4j;

import com.pflager.glut;

public class Window extends glut {
	
	public double innerWidth;
	public double innerHeight;
	
	public Window(String title) {
		glutInit();
	}

    public static void main(String[] args) {
    	new Window("Test");
    }
    
}

