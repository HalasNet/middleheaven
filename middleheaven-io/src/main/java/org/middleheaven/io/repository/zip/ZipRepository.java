/**
 * 
 */
package org.middleheaven.io.repository.zip;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.middleheaven.io.ArrayByteBuffer;
import org.middleheaven.io.ByteBuffer;
import org.middleheaven.io.ManagedIOException;
import org.middleheaven.io.repository.AbstractManagedRepository;
import org.middleheaven.io.repository.ManagedFile;
import org.middleheaven.io.repository.ManagedFilePath;
import org.middleheaven.io.repository.ManagedFileType;

/**
 * 
 */
public class ZipRepository extends  AbstractManagedRepository{

	
	public static ZipRepository fromFile(ManagedFile zipFile){
		return new ZipRepository(zipFile);
	}

	private ManagedFile zipFile;
	
	private ZipRepository (ManagedFile zipFile){
		this.zipFile = zipFile;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadable() {
		return zipFile.isReadable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWriteable() {
		return zipFile.isWriteable();
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
	public Iterable<ManagedFilePath> getRootPaths() {
		Collection<ManagedFilePath>  zipVirtualFiles = new HashSet<ManagedFilePath>();
		ZipInputStream zipIn = null;
		try {			
			zipIn = new ZipInputStream(zipFile.getContent().getInputStream());

			ZipEntry zipEntry;
			while ((zipEntry = zipIn.getNextEntry()) != null) {		
				String entryName = zipEntry.getName();
				String[] path = entryName.split("/");					

				if (path != null && path.length == 1 ) {

					ZipManagedFile file = null;
					String virtualFilePath = "";
					if (path.length > 1 || zipEntry.isDirectory()) {
						//é um diretorio						
						String folder =  path[0];
						virtualFilePath = virtualFilePath+folder;
						file = new ZipManagedFile(this, new ZipEntry(virtualFilePath+"/"),folder, ManagedFileType.FOLDER);
					} else {
						//e um arquivo
						file = new ZipManagedFile(this, zipEntry,path[path.length-1],ManagedFileType.FILE);
					}
				} 
			}			
			return zipVirtualFiles;
		} catch (IOException e) {
			throw  ManagedIOException.manage(e);
		} finally {
			try {
				if(zipIn != null) {
					zipIn.close();
				}
			} catch (IOException e) {
				throw  ManagedIOException.manage(e);
			}
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
	 * @return
	 */
	protected URI getURI() {
		return zipFile.getURI();
	}
	
	InputStream inputStreamOfZipVirtualFile(ZipManagedFile file) throws ManagedIOException {

		if(file.getType().equals(ManagedFileType.VIRTUAL)) {
			return new ByteArrayInputStream(new byte[0]);
		} 
		
		ByteBuffer byteOutPut = new ArrayByteBuffer();

		ZipInputStream zipIn = new ZipInputStream(this.zipFile.getContent().getInputStream());

		try {

			boolean found = false;
			ZipEntry zipEntry;
			while (!found && (zipEntry = zipIn.getNextEntry()) != null) {						
				if(zipEntry.getName().equals(file.getPath())) {		
					doOpenStreamCopy(zipIn, byteOutPut.getOutputStream());
					byteOutPut.getOutputStream().close();
					zipIn.closeEntry();
					found = true;
				}			
			}
			
			if (!found){
				throw new ManagedIOException("File " + file.getURI() + "  does not exist");
			}
			zipIn.close();
			
			return byteOutPut.getInputStream();
		} catch (IOException e) {			
			throw new ManagedIOException(e);
		}		
	}	
	
	private static void doOpenStreamCopy(InputStream in,OutputStream out) throws ManagedIOException {
		try{
			byte[] buffer = new byte[1024 * 4]; //4 Kb
			int n = 0;
			while (-1 != (n = in.read(buffer))) {
				out.write(buffer, 0, n);
			}
			out.flush();

			out.close();
		}catch (IOException e){
			throw ManagedIOException.manage(e);
		}
	}

}
