/**
 * 
 */
package org.middleheaven.instance;

import org.middleheaven.model.annotations.Entity;
import org.middleheaven.model.annotations.Id;

/**
 * 
 */
@Entity
public interface TestBean {

	@Id
	public long getId();
	public void setId(long id); 
	
	public String getName();
	public void setName(String name);
}
