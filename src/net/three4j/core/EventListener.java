package net.three4j.core;

public interface EventListener {
	
	public default void call(Object target, Event event) {}

}
