package net.three4j.unit.src.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.three4j.core.Layers;

public class LayersTests {

	// DPP: I don't really even know what this means
//	@Test
	public void instancing() {

		assertTrue(false, "everything's gonna be alright");

	}

	// PUBLIC STUFF
	@Test
	public void set() {

		Layers a = new Layers();

		a.set(0);
		assertEquals(a.mask, 1, "Set channel 0");

		a.set(1);
		assertEquals(a.mask, 2, "Set channel 1");

		a.set(2);
		assertEquals(a.mask, 4, "Set channel 2");

	}

	@Test
	public void enable() {

		Layers a = new Layers();

		a.set(0);
		a.enable(0);
		assertEquals(a.mask, 1, "Enable channel 0 with mask 0");

		a.set(0);
		a.enable(1);
		assertEquals(a.mask, 3, "Enable channel 1 with mask 0");

		a.set(1);
		a.enable(0);
		assertEquals(a.mask, 3, "Enable channel 0 with mask 1");

		a.set(1);
		a.enable(1);
		assertEquals(a.mask, 2, "Enable channel 1 with mask 1");

	}

	@Test
	public void toggle() {

		Layers a = new Layers();

		a.set(0);
		a.toggle(0);
		assertEquals(a.mask, 0, "Toggle channel 0 with mask 0");

		a.set(0);
		a.toggle(1);
		assertEquals(a.mask, 3, "Toggle channel 1 with mask 0");

		a.set(1);
		a.toggle(0);
		assertEquals(a.mask, 3, "Toggle channel 0 with mask 1");

		a.set(1);
		a.toggle(1);
		assertEquals(a.mask, 0, "Toggle channel 1 with mask 1");

	}

	@Test
	public void disable() {

		Layers a = new Layers();

		a.set(0);
		a.disable(0);
		assertEquals(a.mask, 0, "Disable channel 0 with mask 0");

		a.set(0);
		a.disable(1);
		assertEquals(a.mask, 1, "Disable channel 1 with mask 0");

		a.set(1);
		a.disable(0);
		assertEquals(a.mask, 2, "Disable channel 0 with mask 1");

		a.set(1);
		a.disable(1);
		assertEquals(a.mask, 0, "Disable channel 1 with mask 1");

	}

	@Test
	public void test() {

		Layers a = new Layers();
		Layers b = new Layers();

		assertTrue(a.test(b), "Start out true");

		a.set(1);
		assertFalse(a.test(b), "Set channel 1 in a and fail the QUnit.test");

		b.toggle(1);
		assertTrue(a.test(b), "Toggle channel 1 in b and pass again");

	}

}
