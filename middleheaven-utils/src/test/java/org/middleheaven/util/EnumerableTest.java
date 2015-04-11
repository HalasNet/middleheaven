/**
 * 
 */
package org.middleheaven.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.enumerable.Sink;
import org.middleheaven.collections.enumerable.size.CountableEnumerableSize;
import org.middleheaven.collections.nw.Association;
import org.middleheaven.collections.nw.PairAssociationEntry;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequences;

/**
 * 
 */
public class EnumerableTest {


	
	@Test
	public void testEnumerable(){
		
		Enumerable<Integer> e = Enumerables.asEnumerable(1,2,3,4,5,7,8,9);
		
		assertFalse(e.isEmpty());
		
		for(Integer i : e){
			
		}
		
		assertFalse(e.isEmpty());
	}
	
	private static void assertSize ( String message, int expected, Enumerable<?> enumerable){
		assertEquals(message , expected , ((CountableEnumerableSize)enumerable.getSizeInfo()).getValue().asInteger());
	}
	
	private static void assertSize ( int expected, Enumerable<?> enumerable){
		assertEquals(expected , ((CountableEnumerableSize)enumerable.getSizeInfo()).getValue().asInteger());
	}
	
	@Test
	public void testReverseEnumerable(){
		
		final int limit = 1000000;
		Enumerable<Integer> r = Enumerables.range(1,limit).reverse();
		
		assertFalse(r.isEmpty());
		assertSize(limit, r);
		
		assertFalse(r.isEmpty());
		Enumerable<Integer> e = Enumerables.emptyEnumerable();
		for (int i =1 ; i <= limit; i++){
			e = e.concat(i);
		}
		
		r = e.reverse();
		
		assertFalse(r.isEmpty());
		assertSize(limit, r);
		
	}

	
	@Test
	public void testFilterEnumerable(){
		
		Enumerable<Integer> e = Enumerables.asEnumerable(1).concat(2).concat(4).concat(5).concat(5).concat(7).concat(8).concat(9);
		
		assertFalse(e.isEmpty());
		
		Enumerable<Integer> f = e.filter(obj -> obj % 2 == 0);
		
		assertFalse(f.isEmpty());
		assertSize(3,f);
		
		Enumerable<Integer> d = e.distinct();
		
		assertFalse(d.isEmpty());
		
		List<Integer> list = d.into(Sink.collections()).list();
		assertEquals(7L,d.count());

	}
	
	@Test
	public void testSkip() {
		
		Enumerable<Integer> all = Enumerables.asEnumerable(1,2,3,4,5,6,7,8,10);
		
		assertEquals (Enumerables.asEnumerable(4,5,6,7,8,10) , all.skip(3));
	}

	
	@Test
	public void testTake() {
		
		Enumerable<Integer> all = Enumerables.asEnumerable(1,2,3,4,5,6,7,8,10);
		
		assertEquals (Enumerables.asEnumerable(1,2,3) , all.take(3));
	}
	
	@Test
	public void testSkipTake() {
		
		Enumerable<Integer> all = Enumerables.asEnumerable(1,2,3,4,5,6,7,8,10);
		
		assertEquals (Enumerables.asEnumerable(4,5,6,7) , all.skip(3).take(4));
		
		assertTrue(all.skip(10).isEmpty());
		assertTrue(all.skip(10).take(4).isEmpty());
		assertFalse(all.skip(10).take(4).iterator().hasNext());
	}
	
	@Test
	public void testConcat() {
	
		Enumerable<Integer> a = Enumerables.asEnumerable(1, 2, 3, 4);
		Enumerable<Integer> b = Enumerables.asEnumerable(5, 6, 7, 8);
		Enumerable<Integer> c = Enumerables.asEnumerable(1, 2, 3, 4, 5, 6, 7, 8);
		
		Enumerable<Integer> r = a.concat(b);

		assertTrue(CollectionUtils.equalContents(c.into(Sink.collections()).list(), r.into(Sink.collections()).list()));
		
		r = a.concat(Enumerables.<Integer>emptyEnumerable());
		assertTrue(CollectionUtils.equalContents(a.into(Sink.collections()).list(), r.into(Sink.collections()).list()));
		
		r = Enumerables.<Integer>emptyEnumerable().concat(a);
		assertTrue(CollectionUtils.equalContents(a.into(Sink.collections()).list(), r.into(Sink.collections()).list()));
		
		Enumerable<Number> x = Enumerables.<Number>asEnumerable(1, 2.0d, 3f, 4L);
		Enumerable<Number> u = Enumerables.<Number>asEnumerable(1, 2.0d, 3f, 4L, 5, 6, 7, 8);
		
		Enumerable<Number> d = x.concat(b);
		assertTrue(CollectionUtils.equalContents(u.into(Sink.collections()).list(),d.into(Sink.collections()).list()));
		
		Enumerable<Number> z = b.cast(Number.class);
		d = x.concat(z);
		assertTrue(CollectionUtils.equalContents(u.into(Sink.collections()).list(),d.into(Sink.collections()).list()));
		
		
		r = a.concat(5).concat(6).concat(7).concat(8);
		assertTrue(CollectionUtils.equalContents(c.into(Sink.collections()).list(), r.into(Sink.collections()).list()));
		
	}

	@Test
	public void testDistinct() {
		
		Enumerable<Integer> a = Enumerables.asEnumerable(1, 2, 3, 2, 4, 5, 3, 2 ,1);
		
		assertEquals(5, ((CountableEnumerableSize)a.distinct().getSizeInfo()).getValue().asInteger());
	}
	
	@Test
	public void testToArray() {

		Sequence<Integer> arr = Sequences.of(1, 2, 3, 4);
		
		assertNotNull(arr);
		assertEquals(4, arr.size());
		
		arr = arr.filter(f -> true);
		
		assertNotNull(arr);
		assertEquals(4, arr.size());
		
		arr = arr.concat(Sequences.<Integer>empty());
		
		assertNotNull(arr);
		assertEquals(4, arr.size());
		
		arr = arr.concat(5);
		
		assertNotNull(arr);
		assertEquals(5, arr.size());
	}
	
	@Test
	public void testJoin() {
		Enumerable<Integer> a = Enumerables.asEnumerable(1, 2, 3, 4);
		
		Enumerable<String> b = Enumerables.asEnumerable("1", "2", "3", "4", "%");
		

		for(Association.Entry<String, String> p : a.zip(b, (i, s) -> PairAssociationEntry.of(i.toString(),s))){
			assertEquals(p.getKey(), p.getValue());
		}
		
	}
}
