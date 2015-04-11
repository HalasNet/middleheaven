/**
 * 
 */
package org.middleheaven.injection;

/**
 * 
 */
 class PointSetInjectionModel implements InjectionModel{

	
	private CreationPoint creationPoint;

	public PointSetInjectionModel(){
		
	}
	
	public CreationPoint getCreationPoint(){
		return creationPoint;
	}

	/**
	 * Atributes {@link CreationPoint}.
	 * @param creationPoint the creationPoint to set
	 */
	public void setCreationPoint(CreationPoint creationPoint) {
		this.creationPoint = creationPoint;
	}
	
	
}
