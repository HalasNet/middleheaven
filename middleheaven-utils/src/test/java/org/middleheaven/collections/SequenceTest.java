/**
 * 
 */
package org.middleheaven.collections;

import static org.junit.Assert.*;

import org.junit.Test;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequences;

/**
 * 
 */
public class SequenceTest {

	@Test
	public void testTransformations() {
		
		Sequence<Integer> seq = Sequences.of(1,2,3,4,5,6);
		
		Sequence<Integer> sq = seq.map( i -> i * i);
		
		assertEquals(Sequences.of(1,4,9,16,25,36), sq);
		assertEquals(sq, Sequences.of(1,4,9,16,25,36)); // defferedSequence equals
		
		sq = seq.filter(i -> i % 2 == 0).map( i -> i * i);
		
		assertEquals(Sequences.of(4,16,36), sq);
		assertEquals(sq, Sequences.of(4,16,36)); // defferedSequence equals
		
		sq = seq.zip(seq, (a,b) -> a * b);
		
		assertEquals(Sequences.of(1,4,9,16,25,36), sq);
		assertEquals(sq, Sequences.of(1,4,9,16,25,36)); // defferedSequence equals
		
		assertTrue(sq.contains(36));
		assertEquals(4, sq.indexOf(25));
		assertEquals(4, sq.lastIndexOf(25));
		
		assertEquals(Sequences.of(9,16,25,36), sq.subSequence(2));
		assertEquals(sq.subSequence(2), Sequences.of(9,16,25,36));
		
		assertEquals(Sequences.of(9,16,25), sq.subSequence(2, 3));
		assertEquals(sq.subSequence(2, 1), Sequences.of(9));
	}

}
