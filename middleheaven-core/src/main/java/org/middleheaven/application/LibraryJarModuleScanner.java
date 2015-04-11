/**
 * 
 */
package org.middleheaven.application;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import org.middleheaven.collections.ParamsMap;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.io.ManagedIOException;
import org.middleheaven.io.repository.JarFile;
import org.middleheaven.io.repository.ManagedFile;
import org.middleheaven.reflection.inspection.Introspector;
import org.middleheaven.util.Splitter;
import org.middleheaven.util.Version;

/**
 * 
 */
public class LibraryJarModuleScanner implements ModuleScanner {

	private ManagedFile libraryFolder;
	
	/**
	 * 
	 * Constructor.
	 * @param libraryFolder the folder point to the application folder.
	 */
	public LibraryJarModuleScanner (ManagedFile libraryFolder){
		this.libraryFolder = libraryFolder;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void scan(Collection<Module> modules) {
	
		
//		if (libraryFolder.isWatchable()){
//			WatchService ws = libraryFolder.getRepository().getWatchService();
//
//			WatchEventChannel channel = libraryFolder.register(ws , StandardWatchEvent.ENTRY_CREATED, StandardWatchEvent.ENTRY_DELETED, StandardWatchEvent.ENTRY_MODIFIED);
//
//			this.fileWatchChannelProcessor = new FileWatchChannelProcessor(new FileChangeStrategy() {
//
//				@Override
//				public void onChange(WatchEvent event) {
//					if (StandardWatchEvent.ENTRY_CREATED.equals(event.kind())){
//						ManagedFile file = event.getFile();
//						if (appModulesFilter.classify(file)){
//							setState(ApplicationCycleState.PAUSED);
//							loadModuleFromFile(file);
//							setState(ApplicationCycleState.READY);
//						}
//					}
//				}
//			});
//
//			fileWatchChannelProcessor.add(channel);	
//
//			fileWatchChannelProcessor.start();
//		}

		Enumerable<JarFile> jarFiles = libraryFolder.children().filter (file -> file.getPath().getFileName().endsWith(".jar")).filter(file -> {
			
				try (JarInputStream jis = new JarInputStream(file.getContent().getInputStream())){
					return jis.getManifest()!= null;
				} catch (Exception e) {
					return false;
				}
		}).map(file -> JarFile.from(file));
		
		for (JarFile jar : jarFiles){
			loadModuleFromFile(jar , modules);
		}

		
	}

	private void loadModuleFromFile(final JarFile jar, final Collection<Module> modules) {

		try{
			final URLClassLoader cloader = URLClassLoader.newInstance(new URL[]{jar.getURI().toURL()}, Thread.currentThread().getContextClassLoader());
			
			Manifest manifest = jar.getManifest();

			if (manifest!=null){
				Attributes at = manifest.getMainAttributes();

				if (at.getValue("Application") != null){
					ParamsMap map = new ParamsMap()
					.setParam("Application", at.getValue("Application"))
					.setParam("Application-Modules", at.getValue("Application-Modules"))
					.setParam("Application-Module-Depends", at.getValue("Application-Module-Depends"))
					;
					
					parseAttributes(map, cloader, modules);
				}
			}
			
		}catch (IOException e) {
			ManagedIOException.manage(e);
		} 

	}
	
	private void parseAttributes(Map<String, String> attributes , ClassLoader cloader, Collection<Module> modules){

		String applicationId = attributes.get("Application");
		
		String applicationModulesSignature = attributes.get("Application-Modules");
		String applicationModulesDepends = attributes.get("Application-Module-Depends");
		
	
		parseAndAddModules(applicationId, applicationModulesSignature , applicationModulesDepends, cloader , modules);

	}
	
	
	
	private void parseAndAddModules(String applicationId, String applicationModulesSignature, String applicationModulesDepends, ClassLoader cloader, Collection<Module> modules) {

		
		if (applicationModulesSignature.trim().length() == 0){
			throw new IllegalStateException("No modules found");
		}
		
		Sequence<String> types = Splitter.on(',').trim().split(applicationModulesSignature.trim());
		final Splitter splitter = Splitter.on(":").trim();
		
		for (String type : types){
			
			Sequence<String> parts = splitter.split(type.trim());
			
			ModuleActivator m =  Introspector.of(ModuleActivator.class).load(parts.get(2),cloader).newInstance();
			
			Module module = new Module(applicationId, parts.get(0), Version.valueOf(parts.get(1)), m );
			
			modules.add(module);
			
			if (applicationModulesDepends != null){
		
				for (String depend : Splitter.on(',').split(applicationModulesDepends.trim())){
					
					Sequence<String> defs = splitter.split(depend.trim());
					// TODO control corret number of params 
					ModuleVersion mv = new ModuleVersion(defs.get(0), Version.valueOf(defs.get(1)));
					
					boolean required = true;
					if (defs.size() == 3) {
						required = defs.get(2).equalsIgnoreCase("required");
					} 
					module.addDependency(new ModuleDependency(mv, required));
				}
			}
			
		}

		
	}
	
}
