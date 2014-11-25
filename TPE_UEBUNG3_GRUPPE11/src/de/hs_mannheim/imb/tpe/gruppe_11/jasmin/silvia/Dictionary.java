package de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia;

/**
 * @author Silvia Yildiz, Jasmin Cano
 *
 */
import java.util.function.Function;

public class Dictionary extends AssociativeArrayImpl<String, String> {
	
	class DataCollector {
		
		String[] fieldValues;
		int index;
		
		DataCollector(int count, Function<Node<String, String>, String> projection) {
			fieldValues = new String[count]; // passt Baumgroesse an zu erwartende Wertemenge an
			collectFrom(root, projection); // fuellt das Array
		}
		
		/**
		 * implementiert einen Algorithmus, der die Werte auf dem Feld einsammelt
		 * aufgerufen durch die Methode fieldSelector.
		 * @param node
		 * @param projection - waehlt den Wert eines spezifischen Feldes eines Knoten.
		 */
		void collectFrom(Node<String, String> node, Function<Node<String, String>, String> projection){
			if (node == null) {
				return;
			}
			collectFrom(node.leftChild, projection); // sammelt aus dem linken Knoten
			fieldValues[index++] = projection.apply(node); // sammelt aus unserem Objekt
			collectFrom(node.rightChild, projection); // sammelt aus rechtem Konten
		}
		
	}
	
	public String[] keys() {
		// Projektor als Lambda-Ausdruck umgesetzt und waehlt Schluessel des Knotens aus
		return new DataCollector(this.size(), node -> node.key).fieldValues;
	}
	
	public String[] values() {
		// Projektor als Lambda-Ausdruck umgesetzt und waehlt Wert des Knotens aus
		return new DataCollector(this.size(), node -> node.value).fieldValues;
	}
	
	public class Record {
		private final String key;
		private final String value;
		
		Record(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		String getValue() {
			return value;
		}
	}
	
	public Dictionary.Record[] getRecords() {
		
		class Index {
			int index;
		}
		
		Index idx = new Index();
		Record[] retVal = new Record[size()];
		forEach((key, value) -> retVal[idx.index++] = new Record(key, value));
		return retVal;
	}
	
}
