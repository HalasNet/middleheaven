package org.middleheaven.io.repository.vfs;

import org.apache.commons.vfs.FileName;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.io.repository.ArrayManagedFilePath;
import org.middleheaven.io.repository.ManagedFileRepository;

public class VirtualFileManagedFilePath extends ArrayManagedFilePath {

	public VirtualFileManagedFilePath(ManagedFileRepository repository, FileName name) {
		super(repository, name.getRootURI(), Sequences.of(name.getPath()));
	}

	

}
