package org.middleheaven.util.coersion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.reflection.ReflectedMethod;
import org.middleheaven.reflection.Reflector;

public class TypeCoercingTest {

	private enum A {
		
		AAA,
		BBB,
		CCC
	}
	
	@Test
	public void testEnumCoercing() {
		
		EnumNameTypeCoersor<A> c = new EnumNameTypeCoersor<A>();
		
		//A a = c.coerceForward("AAA",A.class);
		
		Enumerable<ReflectedMethod> all = Reflector.getReflector().reflect(A.class)
				.inspect()
				.staticMethods()
				.named("valueOf")
				.withParametersType(String.class)
				.retriveAll();
				
		assertFalse(all.isEmpty());
		Optional<ReflectedMethod> f = all.tryFirst();
		
		assertNotNull(f);
		assertTrue(f.isPresent());
		Object a = f.get().invokeStatic(Sequences.of("AAA"));
				
		assertNotNull(a);
	}
}
