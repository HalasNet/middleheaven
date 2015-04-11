/**
 * 
 */
package org.middleheaven.quantity.money;

import org.middleheaven.quantity.unit.IncompatibleUnitsException;


/**
 * 
 */
class MoneyUtils {

    static void assertCompatible(Currency a, Currency b) throws IncompatibleUnitsException {
		if (!a.isCompatible(b)){
			throw new IncompatibleUnitsException(a,b);
		}
	}

	
}
