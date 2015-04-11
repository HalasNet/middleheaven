/**
 * 
 */
package org.middleheaven.collections.enumerable.size;


/**
 * Presents a numeric size that can be obtains in constant (O(1)) time. This would be the size of a normal Collection object.
 */
public interface PreciseEnumerableSize extends CountableEnumerableSize , Comparable<PreciseEnumerableSize> {

}
