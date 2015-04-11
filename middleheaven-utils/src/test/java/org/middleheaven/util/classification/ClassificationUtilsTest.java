package org.middleheaven.util.classification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.enumerable.FilteredIterator;
import org.middleheaven.collections.enumerable.Sink;
import org.middleheaven.collections.enumerable.size.CountableEnumerableSize;
import org.middleheaven.util.function.Predicate;

public class ClassificationUtilsTest {

	final Predicate<Integer> predicate = obj ->obj.intValue() % 2 ==0;
	
	@Test 
	public void testArrayIterator(){
		
		Iterator<Integer> it = CollectionUtils.asIterator(1 ,2 ,3 , 4);
		
		Integer r = it.hasNext() ? it.next() : null;
		
		assertEquals(1 ,r);
		
	}
	
	@Test 
	public void testFilteredIterator(){
		
		Iterator<Integer> it = new FilteredIterator<Integer>( CollectionUtils.asIterator(1 ,2 ,3 , 4), predicate);
		
		Integer r = it.hasNext() ? it.next() : null;
		
		assertEquals(2 ,r);
		
	}
	
	private static void assertSize ( int expected, Enumerable<?> enumerable){
		assertEquals(expected , ((CountableEnumerableSize)enumerable.getSizeInfo()).getValue().asInteger());
	}
	
	@Test
	public void testClassificationUtils(){

		Enumerable<Integer> c = Enumerables.asEnumerable(3,4,1,2);
		
		assertSize(4,c);
		
		List<Integer> result = c.filter(predicate).into(Sink.collections()).list();
		
		assertEquals(2,result.size());
		
		assertTrue(CollectionUtils.equalContents(Arrays.asList(4,2), result));
		
		Integer r = c.filter(predicate).findFirst();
		
		assertEquals(Integer.valueOf(4),r);
	
		
		final List<Integer> rest = c.into(Sink.collections()).list();
		
		assertEquals(4,rest.size());
		
		assertTrue(CollectionUtils.equalContents(Arrays.asList(1,2,3,4), c.sort().into(Sink.collections()).list()));
	}
	
	@Test
	public void testWalterFilter(){
		Enumerable<Integer> all = Enumerables.asEnumerable(3,4,1,2,-2, 5,6);
		List<Integer> proof = Arrays.asList(16,4,4,36);
		
		
		List<Integer> result = all.filter(i -> i % 2 == 0).map( i -> i * i).into(Sink.collections()).list();
		
		assertTrue(CollectionUtils.equalContents(proof, result));
		
	}
	
}
