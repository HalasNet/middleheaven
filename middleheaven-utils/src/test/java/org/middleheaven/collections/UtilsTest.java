package org.middleheaven.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.enumerable.Sink;
import org.middleheaven.collections.iterators.ComposedIterator;
import org.middleheaven.collections.nw.Association;
import org.middleheaven.collections.nw.DictionaryAssociation;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequence.SequenceView;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.collections.progression.Progressions;
import org.middleheaven.util.function.BinaryOperator;
import org.middleheaven.util.function.Function;


public class UtilsTest {


	
	@Test
	public void testSequence (){
	
		Sequence<String> s = Sequences.of("a", "b", "c");
		Sequence<String> t = Sequences.of("a").concat("b").concat("c");
		Association<Integer, String> dic = s.asAssociation();
		
		Sequence<?> o = s;
		
		assertTrue(o.contains("a"));
		
		String a = s.get(0);
		Optional<String> oa = dic.tryGet(0);
		
		assertEquals("a", s.get(0));
		assertEquals("a", a);
		assertEquals("a", oa.get());
		
		Association<String, String> g = DictionaryAssociation.with("a", "a").associate("b", "b");
		
		assertEquals("a", g.tryGet("a").get());
		
		for (Sequence.Entry<String> p : s.entries()){
			assertEquals(s.get(p.getIndex()) , p.getValue());
		}
		
		for (Sequence.Entry<String> p : t.entries()){
			assertEquals(s.get(p.getIndex()) , p.getValue());
		}
		
		for (Association.Entry<Integer, String> p : dic.entries()){
			assertEquals(dic.get(p.getKey()) , p.getValue());
		}
		
		for (Association.Entry<String, String> p : g.entries()){
			assertEquals(p.getKey() , p.getValue());
		}
		
		SequenceView<Integer> v = Sequences.of(1,2,3,4,5,6,7,8,9,0,1,1,2,3,4,2,45,345,445).sample(Progressions.from(10).upTo(14));
		
		assertEquals(5, v.size());
		
		assertEquals(Sequences.of(1,1,2,3,4), v);
			
	}
	
	@Test
	public void testIntersect (){
		
		Collection<String> a = new ArrayList<String>(); 
		a.add("A");
		a.add("A");
		a.add("B");
		a.add("C");
		a.add("D");
		
		Collection<String> b = new ArrayList<String>(); 
		b.add("A");
		b.add("B");
		b.add("B");
		b.add("D");
		
		Collection<String> r = new ArrayList<String>(); 
		r.add("A");
		r.add("B");
		r.add("D");
		
		assertTrue(CollectionUtils.equalContents(r, CollectionUtils.intersect(a, b)));
	}
	
	@Test
	public void testTime (){
		
		int elementsCount = 1000; // below this number there is no significant difference
		Collection<String> a = new ArrayList<String>(); 
		addAll(elementsCount, a);
		
		Collection<String> b = new ArrayList<String>(); 
		addAll(elementsCount, b);
		
		long time= System.nanoTime();
		CollectionUtils.intersect(a, b);
		long del = System.nanoTime() - time;
		
		a = new HashSet<String>(); 
		addAll(elementsCount, a);
		
		time= System.nanoTime();
		CollectionUtils.intersect(a, b);
		long del2 = System.nanoTime() - time;
		
		assertTrue("Not Faster" , del2<del);
	
	}
	
	private static void addAll(int quantity , Collection<String> collection){
		for (int i=0; i < quantity;i++){
			collection.add("A" + i);
		}
	}
	@Test
	public void testSpecialIterator(){
		
		List<List<Integer>> lists = new ArrayList<List<Integer>>(); 
		
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		
		List<Integer> b = new ArrayList<Integer>();
		b.add(-1);
		b.add(-2);
		b.add(-3);
		b.add(-4);
		
		lists.add(a);
		lists.add(b);
		
		ComposedIterator<Integer>  it =ComposedIterator.aggregateIterables(lists);
		
		int count =0;
		for (;it.hasNext();){
			it.next();
			count++;
		}
		
		assertEquals(7, count);
		
		
		
	}
	
	@Test
	public void testMapAll(){
		
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		

		final Function<Enumerable<Integer>, Integer> function = i -> Enumerables.asEnumerable(i , i * 10 , i *100);

		Sequence<Integer> result = Enumerables.asEnumerable(a).mapAll(function).into(Sink.collections()).sequence();
		
		assertEquals(9, result.size());
		
		Sequence <Integer> seq = Sequences.of(1,10,100,2,20,200,3,30,300);
		
		assertEquals(seq, result);
		
		final BinaryOperator<Integer> operator = (Integer x, Integer y) -> x + y;

		
		Integer total = Enumerables.asEnumerable(a).mapAll(function).reduce(0, operator);
		
		assertEquals(666, total);
		
		total = Enumerables.asEnumerable(a).mapReduce(0, function, operator);
		
		assertEquals(666, total);
	}
}
