package net.three4j.core;

public class Event {
	public String type;
	public Object target;
	// Class[] attachment; // DPP: Indexable type array
	
	public Event(String type) {
		this.type = type;
		this.target = null;
	}
	
	public Event(Object target, String type) {
		this.type = type;
		this.target = target;
	}
}
