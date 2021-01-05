package net.three4j.core;

public class Event {
	public String type;
	public Object target;
	
	public Event(String type) {
		this.type = type;
		this.target = null;
	}
	
	public void call(Object target, Event event) {}
}
