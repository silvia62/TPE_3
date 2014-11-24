/**
 * 
 */
package de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Definiert generische assoziative Arrays. Die Funktionalit�t ist analog zu 
 * der bei einem SimpleAssociativeArray. Statt int haben wir nun den Typenparameter T
 * f�r die Schl�ssel und den Typenparameter U f�r die Werte. 
 * 
 * Da Instanzen von T nicht unbedingt vergleichbar sein m�ssen, verwendet man anstelle
 * der Objectinstanz ihren Hash-Code. Wir k�nnen hier unterstellen, das zwei Instanzen 
 * derselben Klassenur dann denselben Hash-Code haben, wenn sie logisch dieselbe Instanz
 * sind. Dabei k�nnen die Referenzen auf diese Instanzen verschieden sein. Als Beispiel
 * k�nne Zwichenketten dienen. Zwei verschiedene Zeichenketten s1 und s2, also
 * mit s1 != s2, k�nnen dieselben Zeichen in der gleichen Reihenfolge enthalten. Dann 
 * sind sie logisch gleichwertig, und tats�chlich liefert ihre equals-Methode auch 
 * den Wert 'true'. Mit equals lassen sich also Original und Kopie einer Zeichenkette
 * nicht unterscheiden, wohl aber mit "==".
 * 
 * Wenn sowohl T als auch U f�r den Typ String stehen, dann spricht man von einem 
 * Dictionary.
 * 
 * Um von SimpleAssociativeArrayImpl zu AssociativeArrayImpl zu kommen, muss man alse
 * den Typ int f�r die Schl�ssel �berall durch den Typenparameter T ersetzen und den
 * Typ String durch den Typenparameter U f�r die Werte. Au�erdem sollte man �berall,
 * wo keys mit dem Operator "==" auf Gleicheit verglichen werden, diesen durch die
 * equals-Methode, die statt dessen auf �quivalenz, also logische Gleichwertigkeit, pr�ft,
 * ersetzen. 
 * 
 *  Dort, wo keys mit "<" oder ">" verglichen werden, muss man nun statt dessen die
 *  Hash-Codes der Schl�ssel vergleichen.
 * 
 * @author Silvia Yildiz, Jasmin Cano
 *
 * @param <T> - Typenparameter f�r die Keys
 * @param <U> - Typenparameterf�r die Values
 */

public interface AssociativeArray<T, U> {

	
	public abstract boolean isEmpty();

	public abstract void clear();
	
	public abstract boolean containsKey(T key);

	public abstract boolean containsValue(U value);

	public abstract void put(T key, U value);

	public abstract U get(T key);

	public abstract int size();
	
	public abstract U remove(T key);
	
	public abstract void update(T key, U value);
	
	public abstract void forEach(BiConsumer<T, U> consumer);
	
	public abstract void putAll(AssociativeArray<T, U> source);
	
	public abstract void extractAll(AssociativeArray<T, U> target);
	
	public abstract <V> AssociativeArray<T, V> map(BiFunction<T, U, V> function);

}