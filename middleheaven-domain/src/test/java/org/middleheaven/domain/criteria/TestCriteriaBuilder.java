/**
 * 
 */
package org.middleheaven.domain.criteria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.middleheaven.util.criteria.FieldCriterion;


/**
 * 
 */
public class TestCriteriaBuilder {

	
	@Test
	public void testCriteria (){
		
		final EntityCriteria<TestUser> c = EntityCriteriaBuilder.search(TestUser.class)
				.and(u -> u.getName()).whereText().contains("john")
				.all();
		
		assertNotNull(c);
		
		FieldCriterion fc = (FieldCriterion)c.constraints().criterias().stream().findFirst().get();
		
		assertEquals("name", fc.getFieldName().getDesignation());
		
		

	}
	
	@Test
	public void testManyToOneCriteria (){
		final EntityCriteria<TestPerson> c = EntityCriteriaBuilder.search(TestPerson.class)
				.and(p -> p.getUser()).navigateTo()
					.and(p->p.getName()) .whereText().contains("john")
				.back()
				.all();
		
		assertNotNull(c);
		
		FieldCriterion fc = (FieldCriterion)c.constraints().criterias().stream().findFirst().get();
		
		assertEquals("user", fc.getFieldName().getDesignation());
	}
}
