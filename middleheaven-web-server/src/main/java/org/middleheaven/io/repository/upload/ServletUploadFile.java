/**
 * 
 */
package org.middleheaven.io.repository.upload;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collections;

import javax.servlet.http.Part;

import org.middleheaven.io.ManagedIOException;
import org.middleheaven.io.StreamableContent;
import org.middleheaven.io.repository.AbstractManagedFile;
import org.middleheaven.io.repository.ArrayManagedFilePath;
import org.middleheaven.io.repository.ManagedFile;
import org.middleheaven.io.repository.ManagedFilePath;
import org.middleheaven.io.repository.ManagedFileRepository;
import org.middleheaven.io.repository.ManagedFileType;
import org.middleheaven.io.repository.empty.UnexistantManagedFile;
import org.middleheaven.logging.Logger;

/**
 * 
 */
public class ServletUploadFile extends AbstractManagedFile{

    Part part;
	private ArrayManagedFilePath path;
	private ManagedFile parent;

	/**
	 * Constructor.
	 * @param repositoy
	 */
	protected ServletUploadFile(ManagedFileRepository repositoy, Part part, ManagedFile parent) {
		super(repositoy);
		this.part = part;
		this.parent = parent;
		this.path =  new ArrayManagedFilePath(parent.getPath() , part.getName());
	}

	public boolean equals(Object other){
		return other instanceof ServletUploadFile && equalsOther((ServletUploadFile)other);
	}
	
	private boolean equalsOther(ServletUploadFile other){
		return other.part.equals(this.part);
	}
	
	public int hashCode(){
		return this.part.hashCode();
	}

	@Override
	public boolean delete() {
		try {
			part.delete();
			return true;
		} catch (IOException e) {
			Logger.onBookFor(this.getClass()).error(e, "Error deleting uploaded file");
			return true;
		}
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public StreamableContent doGetContent() {
		return new FileItemManagedFileContent();
	}


	/**
	 * Names are not mutable for this class
	 */
	public boolean canRenameTo(String newName){
		return false;
	}

	@Override
	public ManagedFile getParent() {
		return parent;
	}

	@Override
	public URI getURI() {
		return URI.create(part.getName());
	}

	private class FileItemManagedFileContent extends AbstractRequestFileStreamableContent{

		@Override
		protected InputStream resolveInputStream() throws ManagedIOException{

			try {
				return part.getInputStream();
			} catch (IOException e) {
				throw ManagedIOException.manage(e);
			}
		}
		
		@Override
		public long getSize() throws ManagedIOException {
			return part.getSize();
		}
		
		@Override
		public String getContentType() {
			return part.getContentType();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean doContains(ManagedFile other) {
		throw new ManagedIOException("unsuppported operation");
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ManagedFile doRetriveFromFolder(String path) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doRenameAndChangePath(ManagedFilePath resolveSibling) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ManagedFilePath getPath() {
		return path;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterable<ManagedFile> childrenIterable() {
		return Collections.<ManagedFile>emptySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int childrenCount() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ManagedFile retrive(ManagedFilePath path) throws ManagedIOException {
		return new UnexistantManagedFile(this.getRepository(), path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWatchable() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadable() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWriteable() {
		throw new UnsupportedOperationException("Not supported operation");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ManagedFile doCreateFile() {
		throw new UnsupportedOperationException("Not supported operation");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ManagedFile doCreateFolder(ManagedFile parent) {
		throw new UnsupportedOperationException("Not supported operation");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ManagedFileType getType() {
		return ManagedFileType.FILE;
	}


}
