package net.three4j.unit.src.core;

import org.junit.jupiter.api.Test;

import net.three4j.core.Event;
import net.three4j.core.EventDispatcher;
import net.three4j.core.EventListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventDispatcherTests {

	// @Test // DPP: Not sure what this means?
	public void instancing() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void addEventListener() {

		EventDispatcher eventDispatcher = new EventDispatcher();

		EventListener listener = new EventListener() {
			@Override
			public void call(Object target, Event event) {
			}
		};

		eventDispatcher.addEventListener("anyType", listener);

		assertTrue(eventDispatcher.listeners.get("anyType").size() == 1, "listener was added");
		assertTrue(eventDispatcher.listeners.get("anyType").get(0) == listener, "listener was added");

		eventDispatcher.addEventListener("anyType", listener);

		assertTrue(eventDispatcher.listeners.get("anyType").size() == 1, "can't the same listener twice to same type");
		assertTrue(eventDispatcher.listeners.get("anyType").get(0) == listener, "original listener is still there");
	}

	@Test
	public void hasEventListener() {

		EventDispatcher eventDispatcher = new EventDispatcher();

		EventListener listener = new EventListener() {
			@Override
			public void call(Object target, Event event) {
			}
		};

		eventDispatcher.addEventListener("anyType", listener);

		assertTrue(eventDispatcher.hasEventListener("anyType", listener), "listener was found");
		assertTrue(!eventDispatcher.hasEventListener("anotherType", listener), "listener was not found which is good");

	}

	@Test
	public void removeEventListener() {

		EventDispatcher eventDispatcher = new EventDispatcher();

		EventListener listener = new EventListener() {
			@Override
			public void call(Object target, Event event) {
			}
		};

		eventDispatcher.addEventListener("anyType", listener);
		assertTrue(
				eventDispatcher.listeners.keySet().size() == 1 && eventDispatcher.listeners.get("anyType").size() == 1,
				"if a listener was added, there is a new key");

		eventDispatcher.removeEventListener("anyType", listener);
		assertTrue(eventDispatcher.listeners.get("anyType").size() == 0, "listener was deleted");

		eventDispatcher.removeEventListener("anyType", listener);
		assertTrue(eventDispatcher.listeners.get("anyType").indexOf(listener) == -1, "can't remove a type that is not there.");

		eventDispatcher.removeEventListener("anyType", null);
		assertTrue(eventDispatcher.listeners.get("anyType").size() == 0, "null listeners are ignored");
	}

	int callCount = 0;

	@Test
	public void dispatchEvent() {

		EventDispatcher eventDispatcher = new EventDispatcher();

		callCount = 0;
		
		EventListener listener = new EventListener() {
			@Override
			public void call(Object target, Event event) {
				callCount++;
			}
		};

		eventDispatcher.addEventListener("anyType", listener);
		assertTrue(callCount == 0, "no event, no call");

		eventDispatcher.dispatchEvent(new Event("anyType"));
		assertTrue(callCount == 1, "one event, one call");

		eventDispatcher.dispatchEvent(new Event("anyType"));
		assertTrue(callCount == 2, "two events, two calls");

	}

}
