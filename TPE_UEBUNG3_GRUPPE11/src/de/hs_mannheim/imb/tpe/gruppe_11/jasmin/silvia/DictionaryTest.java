/**
 * 
 */
package de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia;

import static org.junit.Assert.*;

import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

/**
 * Dictionary ist ein spezielle Fall in dem beide Typen String sind. Der
 * Schluessel ist zu einem Wert uebersetzt / maped.
 * 
 * Notiere, dass toString() immer noch ein Schluesselwertpaar irgendwie
 * anordnet. Normalerweise ist die Anordnung nicht einfach zu verstehen weil Sie
 * auf den Hashcode des Schluesselwertes basiert
 * 
 * @author Silvia Yildiz, Jasmin Cano
 * 
 */
public class DictionaryTests {

	Dictionary dictionary;

	// test
	@Before
	public void setUp() {
		dictionary = new Dictionary();
	}

	@Test
	public void getRetrievesAValueByKeyIfThePairWasInsertedBefore() {
		// arrange
		String key = "knife";
		String value = "Messer";
		dictionary.put("spoon", "Löffel");
		dictionary.put("plate", "Teller");
		dictionary.put("fork", "Gabel");
		dictionary.put(key, value);
		dictionary.put("jar", "Glas");
		dictionary.put("pot", "Topf");
		dictionary.put("table-cloth", "Tischdecke");
		dictionary.put("napkin", "Serviette");
		// act
		// assert
		assertEquals(value, dictionary.get(key));
	}

	@Test
	public void keysReturnsCompleteArrayOfKeys() {
		// arrange
		String[] keys = {
			"spoon", "plate", "fork", "knife", "jar", "pot", "table-cloth", "napkin"
		};
		String[] values = {
			"Löffel", "Teller", "Gabel", "Messer", "Glas", "Topf", "Tischdecke", "Serviette"
		};
		for (int i = 0; i < keys.length; ++i)
		{
			dictionary.put(keys[i],  values[i]);
		}
		// act
		String[] keyArray = dictionary.keys();
		Predicate<String> isReturned = (String text) -> {
			for (int i = 0; i < keyArray.length; ++i) {
				if (text.equals(keyArray[i])) {
					return true;
				}
			}
			return false;
		};
		// assert
		for (int i = 0; i < keys.length; ++i) {
			assertTrue(isReturned.test((keys[i])));
		}
	}

	@Test
	public void keysReturnsCompleteArrayOfValues() {
		// arrange
		String[] keys = {
			"spoon", "plate", "fork", "knife", "jar", "pot", "table-cloth", "napkin"
		};
		String[] values = {
			"Löffel", "Teller", "Gabel", "Messer", "Glas", "Topf", "Tischdecke", "Serviette"
		};
		for (int i = 0; i < keys.length; ++i)
		{
			dictionary.put(keys[i],  values[i]);
		}
		// act
		String[] valueArray = dictionary.values();
		Predicate<String> isReturned = (String text) -> {
			for (int i = 0; i < valueArray.length; ++i) {
				if (text.equals(valueArray[i])) {
					return true;
				}
			}
			return false;
		};
		// assert
		for (int i = 0; i < values.length; ++i) {
			assertTrue(isReturned.test((values[i])));
		}
	}

	@Test
	public void twoArraysPopulatedTheSameWayAreEqual() {
		// arrange
		String[] keys = { "spoon", "plate", "fork", "knife", "jar", "pot",
				"table-cloth", "napkin" };
		String[] values = { "Löffel", "Teller", "Gabel", "Messer", "Glas",
				"Topf", "Tischdecke", "Serviette" };
		for (int i = 0; i < keys.length; ++i) {
			dictionary.put(keys[i], values[i]);
		}
		Dictionary second = new Dictionary();
		for (int i = 0; i < keys.length; ++i) {
			second.put(keys[i], values[i]);
		}
		// act
		// assert
		assertTrue(dictionary.equals(second));

	}

	interface Counter {
		void increment();

		int getCount();
	}

	@Test
	public void forEachCanBeUsedToCountEntriesWhereKeyAndValueStartWithTheSameCharacter() {
		// arrange
		Counter counter = new Counter() {
			int count = 0;
			public void increment() {
				count++;
			}
			public int getCount() {
				return count;
			}
		};
		String[] keys = {
			"spoon", "plate", "fork", "knife", "jar", "pot", "table-cloth", "napkin"
		};
		String[] values = {
			"Löffel", "Teller", "Gabel", "Messer", "Glas", "Topf", "Tischdecke", "Serviette"
		};
		for (int i = 0; i < keys.length; ++i)
		{
			dictionary.put(keys[i],  values[i]);
		}
		// act
		dictionary.forEach((key, value) -> {
			if (key.toLowerCase().charAt(0) == value.toLowerCase().charAt(0)) {
				counter.increment();
			}
		});
		// assert
		assertEquals(1, counter.getCount());
	}

}
