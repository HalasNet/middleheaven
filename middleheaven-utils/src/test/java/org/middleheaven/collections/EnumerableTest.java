/**
 * 
 */
package org.middleheaven.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.enumerable.GroupedElements;
import org.middleheaven.collections.enumerable.Sink;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.quantity.math.Real;

/**
 * 
 */
public class EnumerableTest {

	
	@Test
	public void testEnumerableFromEnd() {

		// take the element before the last element
		Enumerable<Integer> a = Enumerables.asEnumerable(1,2,3,4,5,6,7,8);
		
		List<Integer> list = a.into(Sink.collections()).list();
		
		assertEquals(list.get(list.size() - 2), a.reverse().skip(1).findFirst());
		
	}
	
	@Test
	public void testTake() {
		
		Enumerable<Integer> a = Enumerables.asEnumerable(1,2,3);
		

		List<Integer> list = a.take(1).into(Sink.collections()).list();
		
		assertEquals(1 ,list.size());
		
		assertTrue(a.take(1).isSingle());
	}
	
	@Test
	public void testEnumerableFilter() {


		Enumerable<Integer> a = Enumerables.asEnumerable(1,2,3);

		testSequencalNextCallWithoutHasNextCall(a, 3);

		a = a.filter( i -> i % 2 == 1);

		testSequencalNextCallWithoutHasNextCall(a,2);

		Enumerable<Object> big = a.map(i -> BigInteger.valueOf(i));

		testSequencalNextCallWithoutHasNextCall(big,2);
	}


	private <T> void  testSequencalNextCallWithoutHasNextCall (Enumerable<T> a, int expectedSize ){
		Iterator<T> it = a.iterator(); // sequencial iterator call without .hasNext()

		for (int i =0 ; i < expectedSize; i++){
			it.next();
		}

		// at this point the iterator was consumed
		try{
			it.next();
			assertTrue(false); // never runs
		} catch (NoSuchElementException e){
			assertTrue(true);
		}
	}

	@Test
	public void testSinks() {
		
		Enumerable<String> a = Enumerables.asEnumerable("This" , "is" , "fun");
		
		assertEquals("This is fun" , a.into(Sink.ofStrings()).join(" "));
		
		Enumerable<Integer> n = Enumerables.asEnumerable(3, 7, 4, 6 ,5 );
		
		assertEquals(3 , n.into(Sink.ofIntegers()).min());
		assertEquals(7 , n.into(Sink.ofIntegers()).max());
		assertEquals(25 , n.into(Sink.ofIntegers()).sum());
		assertEquals(Real.valueOf(5) , n.into(Sink.ofIntegers()).average());
	}
	
	@Test
	public void testDistinct() {
		
		Enumerable<Integer> n = Enumerables.asEnumerable(1, 2, 3, 2 ,1 ,3, 4, 2, 1,5,6,2 );
		
		testSequencalNextCallWithoutHasNextCall(n,12);
		
		Enumerable<Integer> d = n.distinct();
		
		testSequencalNextCallWithoutHasNextCall(d,6);
	}
	
	@Test
	public void testEnumerableGroupBy() {
		Enumerable<String> a = Enumerables.asEnumerable("Ana","Roberta","Elsa", "Abigail", "Anastásia", "Rita", "Elvira", "Ruth");

		Enumerable<GroupedElements<String, String>> g = a.groupBy(n -> n.substring(0, 1));

		for (GroupedElements<String, String> ge : g){
			Set<String> names = ge.elements().into(Sink.collections()).set();
			if (ge.getKey().equals("A")){
				assertTrue(names.contains("Ana"));
				assertTrue(names.contains("Abigail"));
				assertTrue(names.contains("Anastásia"));
				assertEquals(3, names.size());
			} else if (ge.getKey().equals("R")){
				assertTrue(names.contains("Roberta"));
				assertTrue(names.contains("Rita"));
				assertTrue(names.contains("Ruth"));
				assertEquals(3, names.size());
			} else if (ge.getKey().equals("E")){
				assertTrue(names.contains("Elsa"));
				assertTrue(names.contains("Elvira"));
				assertEquals(2, names.size());
			}
		}

		List<String> names = new ArrayList<>();
		for (GroupedElements<String, String> ge : g){
			for (String s : ge.elements()){
				names.add(s);
			}
		}

		assertEquals(Sequences.of("Ana", "Abigail", "Anastásia", "Roberta","Rita", "Ruth","Elsa", "Elvira"), Sequences.of(names));

		Iterator<GroupedElements<String, String>> it = g.iterator(); // sequencial iterator call without .hasNext()

		assertEquals("A", it.next().getKey());
		assertEquals("R", it.next().getKey());
		assertEquals("E", it.next().getKey());
		
		testSequencalNextCallWithoutHasNextCall(g,3);

		Enumerable<String> v = g.mapAll( el -> el.elements());

		assertEquals(Sequences.of("Ana", "Abigail", "Anastásia", "Roberta","Rita", "Ruth","Elsa", "Elvira"), v.into(Sink.collections()).sequence());

	}
	
	@Test
	public void testEnumerableReverse() {
		
		Enumerable<Integer> a = Enumerables.range(1, 10);
		
		assertEquals(1,a.findFirst());
		assertEquals(10,a.findLast());
		
		Enumerable<Integer> ra = a.reverse();
		
		assertEquals(a,ra.reverse());
		
		assertEquals(10,ra.findFirst());
		assertEquals(10,ra.iterator().next());
		assertEquals(1,ra.findLast());

		Enumerable<Integer> b = Enumerables.range(1, 10).filter(t -> t % 2 == 0);
		
		assertEquals(2,b.findFirst());
	
		assertEquals(10,b.findLast());

		
		Enumerable<Integer> rb = b.reverse();
		
		assertEquals(10,rb.findFirst());
		assertEquals(10,rb.iterator().next());
		assertEquals(2,rb.findLast());

	}
}
