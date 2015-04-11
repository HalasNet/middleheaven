package org.middleheaven.domain.query;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.domain.criteria.EntityCriteria;
import org.middleheaven.util.criteria.ReadStrategy;


public interface QueryExecuter {

	
	public <T> Enumerable<T> retrive(EntityCriteria<T> query, ReadStrategy readStrategy, QueryParametersBag queryParametersBag);
	public <T> long count(EntityCriteria<T> query, QueryParametersBag queryParametersBag);
	public <T> boolean existsAny(EntityCriteria<T> query, QueryParametersBag queryParametersBag);
}
