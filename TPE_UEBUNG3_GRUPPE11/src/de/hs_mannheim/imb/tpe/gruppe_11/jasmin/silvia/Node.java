/**
 * 
 */
package de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia;

/**
 * Dieses Interface verallgemeinert die SimpleNode-Klasse zu einer generischen
 * Klasse, bei denen die Datentypen der Schlüssel und der Werte verschiedene
 * Referenzdatentypen sind, die völlig beliebig ausgewählt werden können.
 * 
 * @see de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia
 * @author Silvia Yildiz, Jasmin Cano
 *
 */
public class Node<T, U> {
	
	T key;
	U value;
	Node<T, U> leftChild;
	Node<T, U> rightChild;
	
	Node(T key, U value) {
		
		this.key = key;
		this.value = value;
		
	}
	
	void add(Node<T,U> node) {
		/* 
		 * Nullwird abgelehnt, könnte auch im übernächsten if abgehandelt werden
		 */
		if (node == null) {
			throw new NullPointerException();
		}
		if (this == node) {
			throw new IllegalArgumentException("Cannot make node child of itself");
		}
		if (key.hashCode() == node.key.hashCode()) { // wenn das legal wäre, also ein update, dann könnten wir auf
			// update im Interface verzichten
			throw new IllegalArgumentException("key exists already!");
		}
		Node<T, U> child = (key.hashCode() < node.key.hashCode()) ? rightChild : leftChild;
		if (child != null) {
			child.add(node);
			return;
		}
		if (key.hashCode() < node.key.hashCode()) {
			rightChild = node;
			return;
		}
		leftChild = node;
	}
	
	U remove(T key) {
		
		if (key.hashCode() < this.key.hashCode()){
			
			if (leftChild.key.hashCode() == key.hashCode()) {
				U value = leftChild.value;
				if (leftChild.leftChild != null) {
					if (leftChild.rightChild != null) { // rechten Zweig retten
						leftChild.leftChild.add(leftChild.rightChild);
					}
					leftChild = leftChild.leftChild;
					return value;
				}
				leftChild = leftChild.rightChild;
				return value;
			}
			return leftChild.remove(key);
		}
		if (rightChild.key.hashCode() == key.hashCode()) {
			U value = rightChild.value;
			if (rightChild.leftChild != null) { // rechten Zweig retten
				if (rightChild.rightChild != null) {
					rightChild.leftChild.add(rightChild.rightChild);
				}
				rightChild = rightChild.leftChild;
				return value;
			}
			rightChild = rightChild.rightChild;
			return value;
		}
		return rightChild.remove(key);
	}

	
	
}
