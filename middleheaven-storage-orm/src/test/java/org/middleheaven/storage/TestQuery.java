package org.middleheaven.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.domain.query.EnumerableQuery;
import org.middleheaven.domain.query.QueryResult;

public class TestQuery {


	
	@Test
	public void testQuery(){
		
		QueryResult<String> q = new EnumerableQuery<String>(Enumerables.asEnumerable("A","B","C","D","E"));
		
		assertFalse(q.isEmpty());
		assertEquals(5L, q.count());
		assertEquals("A", q.fetchFirst());
	
		QueryResult<String> r = q.limit(1, 2);
		
		assertFalse(r.isEmpty());
		assertEquals(2L, r.count());
		assertEquals("A", r.fetchFirst());
		assertEquals(Enumerables.asEnumerable("A","B"), r.fetchAll());
		
		r = q.limit(3, 3);
		
		assertFalse(r.isEmpty());
		assertEquals(3L, r.count());
		assertEquals("C", r.fetchFirst());
		assertEquals(Enumerables.asEnumerable("C","D","E"), r.fetchAll());
		
		r = q.limit(4, 3);
		
		assertFalse(r.isEmpty());
		assertEquals(2L, r.count());
		assertEquals("D", r.fetchFirst());
		assertEquals(Enumerables.asEnumerable("D","E"), r.fetchAll());
		
		r = q.limit(6, 3);
		
		assertTrue(r.isEmpty());
		assertEquals(0L, r.count());
		assertEquals(null, r.fetchFirst());
		assertTrue(r.fetchAll().isEmpty());
	}
}
