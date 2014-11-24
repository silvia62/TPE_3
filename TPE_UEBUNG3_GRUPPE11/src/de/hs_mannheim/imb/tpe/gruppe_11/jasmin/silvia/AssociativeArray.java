/**
 * 
 */
package de.hs_mannheim.imb.tpe.gruppe_11.jasmin.silvia;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Definiert generische assoziative Arrays. Die Funktionalität ist analog zu 
 * der bei einem SimpleAssociativeArray. Statt int haben wir nun den Typenparameter T
 * für die Schlüssel und den Typenparameter U für die Werte. 
 * 
 * Da Instanzen von T nicht unbedingt vergleichbar sein müssen, verwendet man anstelle
 * der Objectinstanz ihren Hash-Code. Wir können hier unterstellen, das zwei Instanzen 
 * derselben Klassenur dann denselben Hash-Code haben, wenn sie logisch dieselbe Instanz
 * sind. Dabei können die Referenzen auf diese Instanzen verschieden sein. Als Beispiel
 * könne Zwichenketten dienen. Zwei verschiedene Zeichenketten s1 und s2, also
 * mit s1 != s2, können dieselben Zeichen in der gleichen Reihenfolge enthalten. Dann 
 * sind sie logisch gleichwertig, und tatsächlich liefert ihre equals-Methode auch 
 * den Wert 'true'. Mit equals lassen sich also Original und Kopie einer Zeichenkette
 * nicht unterscheiden, wohl aber mit "==".
 * 
 * Wenn sowohl T als auch U für den Typ String stehen, dann spricht man von einem 
 * Dictionary.
 * 
 * Um von SimpleAssociativeArrayImpl zu AssociativeArrayImpl zu kommen, muss man alse
 * den Typ int für die Schlüssel überall durch den Typenparameter T ersetzen und den
 * Typ String durch den Typenparameter U für die Werte. Außerdem sollte man überall,
 * wo keys mit dem Operator "==" auf Gleicheit verglichen werden, diesen durch die
 * equals-Methode, die statt dessen auf Äquivalenz, also logische Gleichwertigkeit, prüft,
 * ersetzen. 
 * 
 *  Dort, wo keys mit "<" oder ">" verglichen werden, muss man nun statt dessen die
 *  Hash-Codes der Schlüssel vergleichen.
 * 
 * @author Silvia Yildiz, Jasmin Cano
 *
 * @param <T> - Typenparameter für die Keys
 * @param <U> - Typenparameterfür die Values
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