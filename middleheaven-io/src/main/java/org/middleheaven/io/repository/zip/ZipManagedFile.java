/**
 * 
 */
package org.middleheaven.io.repository.zip;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.zip.ZipEntry;

import org.middleheaven.io.ManagedIOException;
import org.middleheaven.io.StreamableContent;
import org.middleheaven.io.StreamableContentAdapter;
import org.middleheaven.io.repository.AbstractManagedFile;
import org.middleheaven.io.repository.ManagedFile;
import org.middleheaven.io.repository.ManagedFilePath;
import org.middleheaven.io.repository.ManagedFileType;

/**
 * 
 */
public class ZipManagedFile extends AbstractManagedFile{

	private ZipEntry entry;
	private String path;
	private ManagedFileType type;

	/**
	 * Constructor.
	 * @param repository
	 */
	protected ZipManagedFile(ZipRepository repository, ZipEntry entry,String path, ManagedFileType type ) {
		super(repository);
		this.entry = entry;
		this.path = path;
		this.type = type;
	}

	public ZipRepository getRepository(){
		return (ZipRepository)super.getRepository();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ManagedFilePath getPath() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWatchable() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ManagedFileType getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URI getURI() {

		StringBuilder stringURL = new StringBuilder()
		.append(this.getRepository().getURI())
		.append("!/")
		.append(path);		

		try {
			return new URI(stringURL.toString());
		} catch (URISyntaxException e) {
			throw new ManagedIOException(e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ManagedFile retrive(ManagedFilePath path) throws ManagedIOException {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadable() {
		return this.getRepository().isReadable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWriteable() {
		return this.getRepository().isWriteable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StreamableContent doGetContent() {
		return new StreamableContentAdapter(){

			@Override
			protected InputStream resolveInputStream() {
				throw new UnsupportedOperationException("Not implememented yet");
			}

			@Override
			protected OutputStream resolveOutputStream() {
				throw new UnsupportedOperationException("Not implememented yet");
			}
			
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ManagedFile doRetriveFromFolder(String path) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doRenameAndChangePath(ManagedFilePath path) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ManagedFile doCreateFile() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ManagedFile doCreateFolder(ManagedFile parent) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean doContains(ManagedFile other) {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterable<ManagedFile> childrenIterable() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int childrenCount() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

}
