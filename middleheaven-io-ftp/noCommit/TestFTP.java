/**
 * 
 */
package org.middleheaven.io.repository.ftp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Ignore;
import org.junit.Test;
import org.middleheaven.io.ManagedIOException;
import org.middleheaven.io.repository.ManagedFile;
import org.middleheaven.io.repository.ManagedFilePath;
import org.middleheaven.io.repository.ManagedPaths;
import org.middleheaven.io.repository.machine.MachineFiles;

/**
 * 
 */
public class TestFTP {

	FtpRepository repo = new FtpRepository ( new FtpCredentials() {

		@Override
		public boolean isAnonymous() {
			return false;
		}

		@Override
		public String getUsername() {
			return "javabuil";
		}

		@Override
		public String getPassword() {
			return "%Q&4pr|!O}=";
		}

		@Override
		public String getFtpAddress() {
			return "184.107.197.226";
		}
	});
	
	private FTPClient connect() {
		try {
			FTPClient ftp = new FTPClient();
			if (!ftp.isConnected()){
				ftp.connect("184.107.197.226");
				ftp.login( "javabuil", "%Q&4pr|!O}=");
			}

			return ftp;
		} catch (IOException e) {
			throw  ManagedIOException.manage(e);
		}
	}
	
	@Test 
	public void testReadLogs() throws IOException {
		
		ManagedFile folder = repo.retrive(repo.getRootPaths().iterator().next());

		ManagedFile file = folder.retrive("access-logs/javabuilding.com");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ManagedFile local = MachineFiles.resolveFile("C:/JavabuildingDeliveries/In/server-logs/javabuilding/" + format.format(new Date()) + ".log" ).createFile();

		file.copyTo(local);
		
		file = folder.retrive("access-logs/sergiotaborda.javabuilding.com");
		local = MachineFiles.resolveFile("C:/JavabuildingDeliveries/In/server-logs/sergiotaborda.javabuilding/" + format.format(new Date()) + ".log" ).createFile();

		file.copyTo(local);
		
		repo.close();
	}
	
	@Test @Ignore
	public void test() throws IOException {

		for (ManagedFilePath p : repo.getRootPaths()){
			ManagedFile root = repo.retrive(p);

			ManagedFile file = root.retrive("restart-tomcat.sh");

			ManagedFile local = MachineFiles.resolveFile("C:/test.txt").createFile();

			file.copyTo(local);

			assertEquals(103L , local.getSize());

			local.delete();
			
			file = root.retrive("sergiotaborda/license.txt");
			assertTrue(file.exists());
			
			local = MachineFiles.resolveFile("C:/test.txt").createFile();

			file.copyTo(local);

			assertEquals(20314L , local.getSize());


			file = root.retrive("sergiotaborda/doesnotExist.txt");
			
			assertFalse(file.exists());
			
			
			file = root.retrive("TestFolder");
			
			assertFalse(file.exists());
			
			file = file.createFolder();
			
			assertTrue(file.exists());
			
			file.delete();
			
			assertFalse(file.exists());
			
			file = root.retrive("TestFolder/InnerTestFolder");
			
			assertFalse(file.exists());
			
			file = file.createFolder();
			
			assertTrue(file.exists());
			
			ManagedFile testFile = file.retrive("testFile.txt").createFile();
			
			assertTrue(testFile.exists());
			
			local.copyTo(testFile);
			
			assertTrue(testFile.getSize() > 0);
			
			//testFile.delete();
			file.deleteTree();
			
			assertFalse(file.exists());
			local.delete();
		}


		repo.close();

	}

}
