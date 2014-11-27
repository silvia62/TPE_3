package de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AssociativeArrayTest {

	AssociativeArray<Integer, String> a;

	/**
	 * Wird vor jeder Testmethode ausgeführt und initialisiert saa jedes Mal
	 * aufs Neue
	 */
	@Before
	public void setUp() {
		// arranges (at least, partially)
		a = new AssociativeArrayImpl<Integer, String>();
	}

	@Test
	public void newAssociativeArrayIsEmpty() {
		// arrange
		// act
		// assert
		assertTrue(a.isEmpty());
	}

	@Test
	public void newAssociativeArrayHasSizeZero() {
		// arrange
		// act
		// assert
		assertEquals(0, a.size());
	}

	@Test
	public void emptyAssociativeArrayDoesNotFindAnyKey() {
		// arrange
		// act
		int key = 15;
		// assert
		assertFalse(a.containsKey(key));
	}

	@Test
	public void puttingAKeyValuePairIncrementsSizeByOne() {
		// arrange
		int expected = a.size() + 1;
		// act
		a.put(37, "some value");
		// assert
		assertEquals(expected, a.size());
	}

	@Test
	public void keyOfNonNullRootNodeExists() {
		// arrange
		int key = 37;
		a.put(key, "some value");
		// act
		// assert
		assertTrue(a.containsKey(key));
	}

	@Test
	public void valueOfNonNullRootNodeIsFound() {
		// arrange
		int key = 37;
		String value = "some special value";
		a.put(key, value);
		// act
		// assert
		assertEquals(value, a.get(key));
	}

	@Test
	public void existingKeyIsConfirmed() {
		// arrange
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		// assert
		assertTrue(a.containsKey(7));
	}

	@Test
	public void getReturnsValueCorrespondingToKey() {
		// arrange
		String expected = "third node";
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, expected);
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		// assert
		assertEquals(expected, a.get(7));
	}

	@Test
	public void clearLeavesAssociativeArrayEmpty() {
		// arrange
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		a.clear();
		// assert
		assertTrue(a.isEmpty());
		assertEquals(0, a.size());
	}

	@Test
	public void emptyAssociativeArrayPrintsItselfAsEmptyBraces() {
		// arrange
		String expected = "{}";
		// act
		// assert
		assertEquals(expected, a.toString());
	}

	@Test
	public void nonEmptyAssociativeArrayPrintsItselfAsListOrderedByKey() {
		// arrange
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		String expected = "{-255 = fifth node, " + "7 = third node, "
				+ "9 = fourth node, " + "15 = root, " + "23 = second node, "
				+ "47 = sixth node}";
		// act
		String actual = a.toString();
		// assert
		assertEquals(expected, actual);
	}

	@Test
	public void valuePreviouslyAddedCanBeFoundUsingGet() {
		// arrange
		int key = -255;
		String expected = "fifth node";
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(key, expected);
		a.put(47, "sixth node");
		// act
		// assert
		assertEquals(expected, a.get(key));
	}

	@Test
	public void getReturnsNullForNonExistingKey() {
		// arrange
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		// assert
		assertNull(a.get(501));
	}

	@Test
	public void putAcceptsValueNull() {
		// arrange
		int key = 51;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(key, null);
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		// assert
		assertNull(a.get(key));
	}

	@Test
	public void removingANonExistingKeyHasNoEffect() {
		// arrange
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		int currentSize = a.size();
		// act
		a.remove(501);
		// assert
		assertEquals(currentSize, a.size());
	}

	@Test
	public void removingFromEmptyAssociativeArrayHasNoEffect() {
		// arrange
		// act
		a.remove(501);
		// assert
		assertEquals(0, a.size());

	}

	@Test
	public void previouslyExistingKeyValuePairCannotBeRetrievedAnymoreAfterRemoval() {
		// arrange
		int key = 9;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(key, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		a.remove(key);
		// assert
		assertNull(a.get(key));
	}

	@Test(expected = IllegalArgumentException.class)
	public void puttingKeyValuePairWhereKeyExistsAlreadyFails() {
		// arrange
		int key = 9;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(key, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		a.put(key, "other value");
		// assert
		// ...
	}

	@Test
	public void failureOfPutDoesNotChangeSize() {
		// arrange
		int key = 9;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(key, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		int currentSize = a.size();
		// act
		try {
			a.put(key, "other value");
		} catch (Exception e) {
		}
		// assert
		assertEquals(currentSize, a.size());
	}

	@Test
	public void removingExistingKeyValuePairDecrementsSizeByOne() {
		// arrange
		int key = 9;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(key, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		int expectedSize = a.size() - 1;
		// act
		a.remove(key);
		// assert
		assertEquals(expectedSize, a.size());
	}

	@Test
	public void updateModifiesValueForExistingKey() {
		// arrange
		int key = 9;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(key, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		String newValue = "something else";
		// act
		a.update(key, newValue);
		// assert
		assertEquals(newValue, a.get(key));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateForNonExistingKeyFails() {
		// arrange
		int key = 86;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		String newValue = "something else";
		// act
		a.update(key, newValue);
		// assert
	}

	@Test
	public void updateDoesNotChangeSize() {
		// arrange
		int key = 9;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(key, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		int currentSize = a.size();
		// act
		a.update(key, "something else");
		// assert
		assertEquals(currentSize, a.size());
	}

	@Test
	public void containsValueSaysNoIfEmpty() {
		// arrange
		// act
		// assert
		assertFalse(a.containsValue("any value"));
	}

	@Test
	public void containsValueSaysNoIfValueWasNotPreviouslyInserted() {

		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, "third node");
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		String otherValue = "something else";
		// act
		// assert
		assertFalse(a.containsValue(otherValue));
	}

	@Test
	public void containsValueConfirmsPreviouslyInsertedValue() {
		// arrange
		String value = "third node";
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, value);
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		// assert
		assertTrue(a.containsValue(value));
	}

	@Test
	public void containsValueConfirmsAnyOfPreviouslyInsertedEqualValues() {
		// arrange
		String value = "third node";
		a.put(15, "root");
		a.put(23, "second node");
		a.put(7, value);
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		a.put(1, value);
		// act
		// assert
		assertTrue(a.containsValue(value));
	}

	@Test
	public void containsValueSayNoIfPreviouslyInsertedValueWasRemoved() {
		// arrange
		String someValue = "some value";
		int key = 7;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(key, someValue);
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		// act
		a.remove(key);
		// assert
		assertFalse(a.containsValue(someValue));
	}

	@Test
	public void containsValueConfirmsIfOneOfSeveralPreviouslyInsertedEqualValuesWasRemoved() {
		// arrange
		String someValue = "some value";
		int key = 7;
		a.put(15, "root");
		a.put(23, "second node");
		a.put(key, someValue);
		a.put(9, "fourth node");
		a.put(-255, "fifth node");
		a.put(47, "sixth node");
		a.put(1, someValue);
		// act
		a.remove(key);
		// assert
		assertTrue(a.containsValue(someValue));
	}
}
