package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class CircularShifterTest {
	LineStorage inputLineStorage;
	LineStorage afterShiftLineStorage;
	CircularShifter shifter;

	@Before
	public void setUp() {
		inputLineStorage = new LineStorage();
		afterShiftLineStorage = new LineStorage();
		shifter = new CircularShifter(afterShiftLineStorage);
		Set<String> words = new HashSet<>();
		words.add("the");
		words.add("after");
		shifter.setIgnoreWords(words);
		inputLineStorage.addObserver(shifter);
	}

	@Test
	public void test() {
		inputLineStorage.addLine("The Day after Tomorrow");
		assertEquals(2, afterShiftLineStorage.size());

		assertEquals("Day after Tomorrow the", afterShiftLineStorage.get(0).toString());
		assertEquals("Tomorrow the Day after", afterShiftLineStorage.get(1).toString());
	}

	@Test
	public void test1() {
		// test lines input does not contain any of the ignored words
		inputLineStorage.addLine("This is my test case");
		assertEquals(5, afterShiftLineStorage.size());


		assertEquals("This is my test case", afterShiftLineStorage.get(0).toString());
		assertEquals("is my test case This", afterShiftLineStorage.get(1).toString());
		assertEquals("my test case This is", afterShiftLineStorage.get(2).toString());
		assertEquals("test case This is my", afterShiftLineStorage.get(3).toString());
		assertEquals("case This is my test", afterShiftLineStorage.get(4).toString());
	}

	@Test
	public void test2() {
		// test lines input does not contain any of the ignored words
		inputLineStorage.addLine("The after");
		assertEquals(0, afterShiftLineStorage.size());
	}

}
