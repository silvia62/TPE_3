/**
 * 
 */
package de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia;

/**
 * @author Silvia Yildiz, Jasmin Cano
 *
 */
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class AssociativeArrayImpl<T, U> implements AssociativeArray<T, U> {

	Node<T, U> root;
	int nodeCount;
	int hash;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia.AssociativeArray#isEmpty()
	 */
	@Override
	public boolean isEmpty() {

		return root == null;

	}

	@Override
	public boolean containsKey(T key) {

		return (root != null && root.containsKey(key));

	}

	@Override
	public boolean containsValue(U value) {
		return containsValue(root, value);
	}

	/**
	 * helper method
	 */
	private boolean containsValue(Node<T, U> node, U value) {
		if (node == null) {
			return false; // obviously not found
		}
		if (node.value.equals(value)) {
			return true; // found
		}
		if (containsValue(node.leftChild, value)) { // search in left branch
			return true; // found in left branch
		}
		return containsValue(node.rightChild, value); // search in right branch
														// - return whatever
														// result
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia.AssociativeArray#clear()
	 */
	@Override
	public void clear() {

		root = null;
		nodeCount = 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia.AssociativeArray#put(int,
	 * java.lang.String)
	 */
	@Override
	public void put(T key, U value) {

		Node<T, U> node = new Node<>(key, value);

		if (root == null) {
			root = node;
			adjustState(node.key, false);
			return;
		}

		root.add(node);
		adjustState(node.key, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia.AssociativeArray#get(int)
	 */
	@Override
	public U get(T key) {

		// Node found;
		// if (root == null || (found = root.findNode(key)) == null) {
		// return null;
		// }
		// return found.value;

		if (root == null) {
			return null;
		}
		Node<T, U> found = root.findNode(key);
		if (found == null) {
			return null;
		}
		return found.value;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia.AssociativeArray#size()
	 */
	@Override
	public int size() {

		return nodeCount;

	}

	/**
	 * Wir beschweren uns nicht, wenn der Schlüssel fehlt!
	 */
	@Override
	public U remove(T key) {

		// wenn die root nicht existiert, dann können wir den key auch nicht
		// finden
		// wenn der key nicht vorhanden ist, sind wir auch fertig
		if (root == null || !root.containsKey(key)) {
			return null;
		}
		// root existiert
		// key existiert auch
		if (root.key.hashCode() == key.hashCode()) { // remove root and arrange
														// tree
			U value = root.value; // merken für später
			if (root.leftChild != null) { // linker Knoten existiert und wird
											// neue root
				if (root.rightChild != null) { // wenn der rechte Knoten
												// existiert, wird er unter dem
												// linken eingehängt
					root.leftChild.add(root.rightChild);
					root = root.leftChild; // linker Knoten wird neue root
					adjustState(key, true);
					return value;
				}
			}
			// linker Knoten existiert nicht, also wird der rechte die neue root
			// ist auch o.k., wenn der rechte Knoten null ist
			root = root.rightChild;
			adjustState(key, true);
			return value;
		}
		// root war's nicht, also delegieren
		U value = root.remove(key);
		// Aufatmen: nichts ist schiefgegangen, also Zustand aktualisieren
		adjustState(key, true);
		return value;
	}

	private void adjustState(T key, boolean forRemoval) {
		if (forRemoval) {
			nodeCount--;
		} else {
			nodeCount++;
		}
		hash ^= key.hashCode();
	}

	@Override
	public void update(T key, U value) {

		if (root == null) {
			throw new RuntimeException("tree is empty!");
		}

		if (!root.containsKey(key)) {
			throw new IllegalArgumentException("key does not exist!");
		}

		Node<T, U> node = (root.key == key) ? root : root.findNode(key);
		node.value = value;
	}

	class Iterator {

		Object[] keys;
		int index;

		Iterator() {
			keys = new Object[size()]; // make tree large enough to hold all
										// values to be expected
			collectFrom(root); // fill the array
			index = 0;
		}

		/**
		 * implements an algorithm which collects the values of the field
		 * retrieved by the function fieldSelector by a depth-first approach
		 * 
		 * @param node
		 * @param projection
		 *            - selects the value of a specific field of a node
		 */
		void collectFrom(Node<T, U> node) {
			if (node == null) {
				return;
			}
			collectFrom(node.leftChild); // collect from left branch
			keys[index++] = node.key; // collect from this
			collectFrom(node.rightChild); // collect from right branch
		}

		boolean hasNext() {
			return index < keys.length;
		}

		@SuppressWarnings("unchecked")
		T next() {
			return (T) keys[index++];
		}

	}

	@Override
	public void forEach(BiConsumer<T, U> consumer) {

		Iterator it = new Iterator();
		while (it.hasNext()) {
			T key = it.next();
			consumer.accept(key, get(key));
		}
	}

	@Override
	public void putAll(AssociativeArray<T, U> source) {

		source.forEach((T key, U value) -> put(key, value));
		
	}

	@Override
	public void extractAll(AssociativeArray<T, U> target) {
		
		forEach((T key, U value) -> target.put(key, value));
		
	}

	@Override
	public <V> AssociativeArray<T, V> map(BiFunction<T, U, V> f) {
		
		AssociativeArray<T, V> retVal = new AssociativeArrayImpl<>();
		forEach((T key, U value) -> retVal.put(key, f.apply(key, value)));
		return retVal;
	}

	@Override
	/**
	 * two arrays are considered equal if they contain the same key-value
	 * pairs.
	 * 
	 * @param object
	 * @return
	 */
	public boolean equals(Object object) {

		if (!(object instanceof AssociativeArrayImpl<?, ?>)) {
			return false; // wrong type
		}
		@SuppressWarnings("unchecked")
		// The compiler generates raw types
		// so it will not be recognized if
		// object's type parameters are the same as
		// T and U, respectively ("type erasure").
		// If the implementation of equals of any type
		// involved does not return false if the other
		// object has a different type, then that
		// implementation of equals is logically incorrect!!
		AssociativeArrayImpl<T, U> aa = (AssociativeArrayImpl<T, U>) object;
		if (size() != aa.size()) {
			return false;
		}
		if (root == null) {
			return true;
		}
		return isContained(root, aa.root);
	}

	/**
	 * helper method traverses tree checks to see if each key-value pair also
	 * exists exists in the other array
	 * 
	 * @param node
	 * @param otherRoot
	 * @return
	 */
	boolean isContained(Node<T, U> node, Node<T, U> otherRoot) {
		if (node == null) {
			return true;
		}
		Node<T, U> nd = otherRoot.findNode(node.key);
		if (nd == null || !node.value.equals(nd.value)) {
			return false;
		}
		return isContained(node.leftChild, otherRoot)
				&& isContained(node.rightChild, otherRoot);
	}

	@Override
	public int hashCode() {
		return hash;
		// Calculating the hash code dynamically on each call
		// is very inefficient. Using adjustState() instead
		// makes the code slightly more complex in a few places
		// (put() and remove()), but results in much faster code.
		// Note that this refactoring lets us still pass all the
		// unit tests.
		// return hashCode(root);
	}

	/**
	 * helper method hash codes of left branch, key, and right branch are simply
	 * XORed.
	 * 
	 * @param node
	 * @return 0 if node is null, its hash code otherwise
	 */
	int hashCode(Node<T, U> node) {
		return (node == null) ? 0 : node.hashCode();
	}

}
