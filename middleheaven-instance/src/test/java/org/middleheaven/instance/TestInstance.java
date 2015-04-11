package org.middleheaven.instance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.middleheaven.domain.model.DomainModel;
import org.middleheaven.domain.model.DomainModelBuilder;
import org.middleheaven.domain.model.EntityModel;
import org.middleheaven.reflection.ClassSet;

/**
 * 
 */

/**
 * 
 */
public class TestInstance {

	@Test
	public void testInstance() {
		
		ClassSet classSet = new ClassSet();
		classSet.add(TestBean.class);
		
		DomainModel model = new DomainModelBuilder().build(classSet);
		
		EntityModel theModel = model.getModelFor(TestBean.class.getName());
		
		assertNotNull(theModel);
		
		InstanceFactory factory = new DomainModelInstanceFactory(model);
		
		Instance<TestBean> instance = factory.CreateInstance(TestBean.class);
		
		assertNotNull(instance);
		
		TestBean test = instance.cast();
		
		assertNotNull(test);
		
		test.setName("Test Name");
		
		assertEquals("Test Name", test.getName());
		assertEquals("Test Name", instance.getFieldValue("Name"));
		
		Instance<TestBean> instance2 =  InstanceUtils.unCast(test);
	
		assertEquals("Test Name", instance2.getFieldValue("Name"));
		
		assertNotNull(instance2);
	}

}
