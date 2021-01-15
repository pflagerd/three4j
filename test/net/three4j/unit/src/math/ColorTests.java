package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.three4j.math.Color;

import static net.three4j.unit.src.math.ConstantsTests.eps;

//import { CONSOLE_LEVEL } from "../../utils/console-wrapper";

public class ColorTests {

	@Test
	public void Instancing() {

		// default ctor
		Color c = new Color();
		assertTrue(c.r() == 1., "Red: " + c.r());
		assertTrue(c.g() == 1., "Green: " + c.g());
		assertTrue(c.b() == 1., "Blue: " + c.b());

		// rgb ctor
		c = new Color(1., 1., 1.);
		assertTrue(c.r() == 1., "Passed");
		assertTrue(c.g() == 1., "Passed");
		assertTrue(c.b() == 1., "Passed");
	}

	@Test
	public void Color_NAMES() {

		assertTrue(Color.NAMES.aliceblue == 0xF0F8FF, "Exposed Color.NAMES");

	}

	@Test
	public void isColor() {

		Color a = new Color();
		assertTrue(a instanceof Color == true, "Passed!");

		Object b = new Object();
		assertFalse(b instanceof Color, "Passed!");

	}

	@Test
	public void set() {

		Color a = new Color();
		Color b = new Color(0.5, 0, 0);
		Color c = new Color(0xFF0000);
		Color d = new Color(0, 1.0, 0);

		a.set(b);
		assertTrue(a.equals(b), "Set with Color instance");

		a.set(0xFF0000);
		assertTrue(a.equals(c), "Set with number");

		a.set("rgb(0,255,0)");
		assertTrue(a.equals(d), "Set with style");

	}

	@Test
	public void setScalar() {

		Color c = new Color();
		c.setScalar(0.5);
		assertTrue(c.r() == 0.5, "Red: " + c.r());
		assertTrue(c.g() == 0.5, "Green: " + c.g());
		assertTrue(c.b() == 0.5, "Blue: " + c.b());

	}

	@Test
	public void setHex() {

		Color c = new Color();
		c.setHex(0xFA8072);
		assertTrue(c.getHex() == 0xFA8072, "Hex: " + c.getHex());
		assertTrue(c.r() == (double)0xFA / 0xFF, "Red: " + c.r());
		assertTrue(c.g() == (double)0x80 / 0xFF, "Green: " + c.g());
		assertTrue(c.b() == (double)0x72 / 0xFF, "Blue: " + c.b());

	}

	@Test
	public void setRGB() {

		Color c = new Color();
		c.setRGB(0.3, 0.5, 0.7);
		assertTrue(c.r() == 0.3, "Red: " + c.r());
		assertTrue(c.g() == 0.5, "Green: " + c.g());
		assertTrue(c.b() == 0.7, "Blue: " + c.b());

	}

	@Test
	public void setHSL() {

		Color c = new Color();
		Color.HSL hsl = new Color.HSL(0, 0, 0);
		c.setHSL(0.75, 1.0, 0.25);
		c.getHSL(hsl);

		assertTrue(hsl.h() == 0.75, "hue: " + hsl.h());
		assertTrue(hsl.s() == 1.00, "saturation: " + hsl.s());
		assertTrue(hsl.l() == 0.25, "lightness: " + hsl.l());

	}

	@Test
	public void setStyle() {

		Color a = new Color();

		Color b = new Color(8 / 255, 25 / 255, 178 / 255);
		a.setStyle("rgb(8,25,178)");
		assertTrue(a.equals(b), "Passed");

		b = new Color(8 / 255, 25 / 255, 178 / 255);
		a.setStyle("rgba(8,25,178,200)");
		assertTrue(a.equals(b), "Passed");

		Color.HSL hsl = new Color.HSL(0, 0, 0);
		a.setStyle("hsl(270,50%,75%)");
		a.getHSL(hsl);
		assertTrue(hsl.h() == 0.75, "hue: " + hsl.h());
		assertTrue(hsl.s() == 0.5, "saturation: " + hsl.s());
		assertTrue(hsl.l() == 0.75, "lightness: " + hsl.l());

		hsl = new Color.HSL(0, 0, 0);
		a.setStyle("hsl(270,50%,75%)");
		a.getHSL(hsl);
		assertTrue(hsl.h() == 0.75, "hue: " + hsl.h());
		assertTrue(hsl.s() == 0.5, "saturation: " + hsl.s());
		assertTrue(hsl.l() == 0.75, "lightness: " + hsl.l());

		a.setStyle("#F8A");
		assertTrue(a.r() == 0xFF / 255, "Red: " + a.r());
		assertTrue(a.g() == 0x88 / 255, "Green: " + a.g());
		assertTrue(a.b() == 0xAA / 255, "Blue: " + a.b());

		a.setStyle("#F8ABC1");
		assertTrue(a.r() == 0xF8 / 255, "Red: " + a.r());
		assertTrue(a.g() == 0xAB / 255, "Green: " + a.g());
		assertTrue(a.b() == 0xC1 / 255, "Blue: " + a.b());

		a.setStyle("aliceblue");
		assertTrue(a.r() == 0xF0 / 255, "Red: " + a.r());
		assertTrue(a.g() == 0xF8 / 255, "Green: " + a.g());
		assertTrue(a.b() == 0xFF / 255, "Blue: " + a.b());

	}

	@Test
	public void setColorName() {

		Color c = new Color();
		Color res = c.setColorName("aliceblue");

		assertTrue(c.getHex() == 0xF0F8FF, "Hex: " + c.getHex());
		assertTrue(c == res, "Returns Self");

	}

	@Test
	public void $clone() {

		Color c = new Color("teal");
		Color c2 = c.clone();
		assertTrue(c2.getHex() == 0x008080, "Hex c2: " + c2.getHex());

	}

	@Test
	public void copy() {

		Color a = new Color("teal");
		Color b = new Color();
		b.copy(a);
		assertTrue(b.r() == 0x00 / 255., "Red: " + b.r());
		assertTrue(b.g() == 0x80 / 255., "Green: " + b.g());
		assertTrue(b.b() == 0x80 / 255., "Blue: " + b.b());

	}

	@Test
	public void copyGammaToLinear() {

		Color c = new Color();
		Color c2 = new Color();
		c2.setRGB(0.3, 0.5, 0.9);
		c.copyGammaToLinear(c2);
		assertTrue(c.r() == 0.09, "Red c: " + c.r() + " Red c2: " + c2.r());
		assertTrue(c.g() == 0.25, "Green c: " + c.g() + " Green c2: " + c2.g());
		assertTrue(c.b() == 0.81, "Blue c: " + c.b() + " Blue c2: " + c2.b());

	}

	@Test
	public void copyLinearToGamma() {

		Color c = new Color();
		Color c2 = new Color();
		c2.setRGB(0.09, 0.25, 0.81);
		c.copyLinearToGamma(c2);
		assertTrue(c.r() == 0.3, "Red c: " + c.r() + " Red c2: " + c2.r());
		assertTrue(c.g() == 0.5, "Green c: " + c.g() + " Green c2: " + c2.g());
		assertTrue(c.b() == 0.9, "Blue c: " + c.b() + " Blue c2: " + c2.b());

	}

	@Test
	public void convertGammaToLinear() {

		Color c = new Color();
		c.setRGB(0.3, 0.5, 0.9);
		c.convertGammaToLinear();
		assertTrue(c.r() == 0.09, "Red: " + c.r());
		assertTrue(c.g() == 0.25, "Green: " + c.g());
		assertTrue(c.b() == 0.81, "Blue: " + c.b());

	}

	@Test
	public void convertLinearToGamma() {

		Color c = new Color();
		c.setRGB(4., 9., 16.);
		c.convertLinearToGamma();
		assertTrue(c.r() == 2., "Red: " + c.r());
		assertTrue(c.g() == 3., "Green: " + c.g());
		assertTrue(c.b() == 4., "Blue: " + c.b());

	}

	@Test
	public void getHex() {

		Color c = new Color("red");
		int res = c.getHex();
		assertTrue(res == 0xFF0000, "Hex: " + res);

	}

	@Test
	public void getHexString() {

		Color c = new Color("tomato");
		String res = c.getHexString();
		assertTrue(res.contentEquals("ff6347"), "Hex: " + res);

	}

	@Test
	public void getHSL() {

		Color c = new Color(0x80ffff);
		Color.HSL hsl = new Color.HSL(0, 0, 0);
		c.getHSL(hsl);

		assertTrue(hsl.h() == 0.5, "hue: " + hsl.h());
		assertTrue(hsl.s() == 1.0, "saturation: " + hsl.s());
		assertTrue((Math.round((int) (hsl.l()) * 100) / 100) == 0.75, "lightness: " + hsl.l());

	}

	@Test
	public void getStyle() {

		Color c = new Color("plum");
		String res = c.getStyle();
		assertTrue(res.contentEquals("rgb(221,160,221)"), "style: " + res);

	}

	@Test
	public void offsetHSL() {

		Color a = new Color("hsl(120,50%,50%)");
		Color b = new Color(0.36, 0.84, 0.648);

		a.offsetHSL(0.1, 0.1, 0.1);

		assertTrue(Math.abs(a.r() - b.r()) <= eps, "Check r");
		assertTrue(Math.abs(a.g() - b.g()) <= eps, "Check g");
		assertTrue(Math.abs(a.b() - b.b()) <= eps, "Check b");

	}

	@Test
	public void add() {

		Color a = new Color(0x0000FF);
		Color b = new Color(0xFF0000);
		Color c = new Color(0xFF00FF);

		a.add(b);

		assertTrue(a.equals(c), "Check new value");

	}

	@Test
	public void addColors() {

		Color a = new Color(0x0000FF);
		Color b = new Color(0xFF0000);
		Color c = new Color(0xFF00FF);
		Color d = new Color();

		d.addColors(a, b);

		assertTrue(d.equals(c), "Passed");

	}

	@Test
	public void addScalar() {

		Color a = new Color(0.1, 0.0, 0.0);
		Color b = new Color(0.6, 0.5, 0.5);

		a.addScalar(0.5);

		assertTrue(a.equals(b), "Check new value");

	}

	@Test
	public void sub() {

		Color a = new Color(0x0000CC);
		Color b = new Color(0xFF0000);
		Color c = new Color(0x0000AA);

		a.sub(b);
		assertEquals(a.getHex(), 0xCC, "Difference too large");

		a.sub(c);
		assertEquals(a.getHex(), 0x22, "Difference fine");

	}

	@Test
	public void multiply() {

		Color a = new Color(1, 0, 0.5);
		Color b = new Color(0.5, 1, 0.5);
		Color c = new Color(0.5, 0, 0.25);

		a.multiply(b);
		assertTrue(a.equals(c), "Check new value");

	}

	@Test
	public void multiplyScalar() {

		Color a = new Color(0.25, 0, 0.5);
		Color b = new Color(0.5, 0, 1);

		a.multiplyScalar(2);
		assertTrue(a.equals(b), "Check new value");

	}

	@Test
	public void copyHex() {

		Color c = new Color();
		Color c2 = new Color(0xF5FFFA);
		c.copy(c2);
		assertTrue(c.getHex() == c2.getHex(), "Hex c: " + c.getHex() + " Hex c2: " + c2.getHex());

	}

	@Test
	public void copyColorString() {

		Color c = new Color();
		Color c2 = new Color("ivory");
		c.copy(c2);
		assertTrue(c.getHex() == c2.getHex(), "Hex c: " + c.getHex() + " Hex c2: " + c2.getHex());

	}

	@Test
	public void lerp() {

		Color c = new Color();
		Color c2 = new Color();
		c.setRGB(0, 0, 0);
		c.lerp(c2, 0.2);
		assertTrue(c.r() == 0.2, "Red: " + c.r());
		assertTrue(c.g() == 0.2, "Green: " + c.g());
		assertTrue(c.b() == 0.2, "Blue: " + c.b());

	}

	@Test
	public void equals() {

		Color a = new Color(0.5, 0.0, 1.0);
		Color b = new Color(0.5, 1.0, 0.0);

		assertEquals(a.r(), b.r(), "Components: r is equal");
		assertNotEquals(a.g(), b.g(), "Components: g is not equal");
		assertNotEquals(a.b(), b.b(), "Components: b is not equal");

		assertFalse(a.equals(b), "equals(): a not equal b");
		assertFalse(b.equals(a), "equals(): b not equal a");

		a.copy(b);
		assertEquals(a.r(), b.r(), "Components after copy(): r is equal");
		assertEquals(a.g(), b.g(), "Components after copy(): g is equal");
		assertEquals(a.b(), b.b(), "Components after copy(): b is equal");

		assertTrue(a.equals(b), "equals() after copy(): a equals b");
		assertTrue(b.equals(a), "equals() after copy(): b equals a");

	}

	@Test
	public void fromArray() {

		Color a = new Color();
		double[] array = { 0.5, 0.6, 0.7, 0, 1, 0 };

		a.fromArray(array);
		assertEquals(a.r(), 0.5, "No offset: check r");
		assertEquals(a.g(), 0.6, "No offset: check g");
		assertEquals(a.b(), 0.7, "No offset: check b");

		a.fromArray(array, 3);
		assertEquals(a.r(), 0, "With offset: check r");
		assertEquals(a.g(), 1, "With offset: check g");
		assertEquals(a.b(), 0, "With offset: check b");

	}

	@Test
	public void toArray() {

		double r = 0.5, g = 1.0, b = 0.0;
		Color a = new Color(r, g, b);

		double[] array = a.toArray();
		assertEquals(array[0], r, "No array, no offset: check r");
		assertEquals(array[1], g, "No array, no offset: check g");
		assertEquals(array[2], b, "No array, no offset: check b");

		array = new double[3];
		a.toArray(array);
		assertEquals(array[0], r, "With array, no offset: check r");
		assertEquals(array[1], g, "With array, no offset: check g");
		assertEquals(array[2], b, "With array, no offset: check b");

		array = new double[4];
		a.toArray(array, 1);
		assertEquals(array[0], 0, "With array and offset: check [0]");
		assertEquals(array[1], r, "With array and offset: check r");
		assertEquals(array[2], g, "With array and offset: check g");
		assertEquals(array[3], b, "With array and offset: check b");

	}

	@Test
	public void toJSON() {

		Color a = new Color(0.0, 0.0, 0.0);
		Color b = new Color(0.0, 0.5, 0.0);
		Color c = new Color(1.0, 0.0, 0.0);
		Color d = new Color(1.0, 1.0, 1.0);

		assertEquals(a.toJSON(), 0x000000, "Check black");
		assertEquals(b.toJSON(), 0x007F00, "Check half-blue");
		assertEquals(c.toJSON(), 0xFF0000, "Check red");
		assertEquals(d.toJSON(), 0xFFFFFF, "Check white");

	}

	// OTHERS
	@Test
	public void setWithNum() {

		Color c = new Color();
		c.set(0xFF0000);
		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setWithString() {

		Color c = new Color();
		c.set("silver");
		assertTrue(c.getHex() == 0xC0C0C0, "Hex c: " + c.getHex());

	}

	@Test
	public void setStyleRGBRed() {

		Color c = new Color();
		c.setStyle("rgb(255,0,0)");
		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setStyleRGBARed() {

		Color c = new Color();

//			console.level = CONSOLE_LEVEL.ERROR;
		c.setStyle("rgba(255,0,0,0.5)");
//			console.level = CONSOLE_LEVEL.DEFAULT;

		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setStyleRGBRedWithSpaces() {

		Color c = new Color();
		c.setStyle("rgb( 255 , 0,   0 )");
		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setStyleRGBARedWithSpaces() {

		Color c = new Color();
		c.setStyle("rgba( 255,  0,  0  , 1 )");
		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setStyleRGBPercent() {

		Color c = new Color();
		c.setStyle("rgb(100%,50%,10%)");
		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0.5, "Green: " + c.g());
		assertTrue(c.b() == 0.1, "Blue: " + c.b());

	}

	@Test
	public void setStyleRGBAPercent() {

		Color c = new Color();

//			console.level = CONSOLE_LEVEL.ERROR;
		c.setStyle("rgba(100%,50%,10%, 0.5)");
//			console.level = CONSOLE_LEVEL.DEFAULT;

		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0.5, "Green: " + c.g());
		assertTrue(c.b() == 0.1, "Blue: " + c.b());

	}

	@Test
	public void setStyleRGBPercentWithSpaces() {

		Color c = new Color();
		c.setStyle("rgb( 100% ,50%  , 10% )");
		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0.5, "Green: " + c.g());
		assertTrue(c.b() == 0.1, "Blue: " + c.b());

	}

	@Test
	public void setStyleRGBAPercentWithSpaces() {

		Color c = new Color();

//			console.level = CONSOLE_LEVEL.ERROR;
		c.setStyle("rgba( 100% ,50%  ,  10%, 0.5 )");
//			console.level = CONSOLE_LEVEL.DEFAULT;

		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0.5, "Green: " + c.g());
		assertTrue(c.b() == 0.1, "Blue: " + c.b());

	}

	@Test
	public void setStyleHSLRed() {

		Color c = new Color();
		c.setStyle("hsl(360,100%,50%)");
		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setStyleHSLARed() {

		Color c = new Color();

//			console.level = CONSOLE_LEVEL.ERROR;
		c.setStyle("hsla(360,100%,50%,0.5)");
//			console.level = CONSOLE_LEVEL.DEFAULT;

		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setStyleHSLRedWithSpaces() {

		Color c = new Color();
		c.setStyle("hsl(360,  100% , 50% )");
		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setStyleHSLARedWithSpaces() {

		Color c = new Color();

//			console.level = CONSOLE_LEVEL.ERROR;
		c.setStyle("hsla( 360,  100% , 50%,  0.5 )");
//			console.level = CONSOLE_LEVEL.DEFAULT;

		assertTrue(c.r() == 1, "Red: " + c.r());
		assertTrue(c.g() == 0, "Green: " + c.g());
		assertTrue(c.b() == 0, "Blue: " + c.b());

	}

	@Test
	public void setStyleHexSkyBlue() {

		Color c = new Color();
		c.setStyle("#87CEEB");
		assertTrue(c.getHex() == 0x87CEEB, "Hex c: " + c.getHex());

	}

	@Test
	public void setStyleHexSkyBlueMixed() {

		Color c = new Color();
		c.setStyle("#87cEeB");
		assertTrue(c.getHex() == 0x87CEEB, "Hex c: " + c.getHex());

	}

	@Test
	public void setStyleHex2Olive() {

		Color c = new Color();
		c.setStyle("#F00");
		assertTrue(c.getHex() == 0xFF0000, "Hex c: " + c.getHex());

	}

	@Test
	public void setStyleHex2OliveMixed() {

		Color c = new Color();
		c.setStyle("#f00");
		assertTrue(c.getHex() == 0xFF0000, "Hex c: " + c.getHex());

	}

	@Test
	public void setStyleColorName() {

		Color c = new Color();
		c.setStyle("powderblue");
		assertTrue(c.getHex() == 0xB0E0E6, "Hex c: " + c.getHex());

	}

}
