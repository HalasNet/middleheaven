/**
 * 
 */
package org.middleheaven.io.repository;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.util.Splitter;

/**
 * 
 */
public class ManagedPaths {

	
	public static ManagedFilePath path(ManagedFileRepository repository , CharSequence path){
		Sequence<String> result = Splitter.on('/').split(path);
		
		return new ArrayManagedFilePath(repository, result.get(0), result.subSequence(1));
	}
}
