package loader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class imgLoader {
	String PATH = "resource/img";
	public Map<String, File> fileMap = new HashMap<String, File>();
	public void load() {
		File f = new File(PATH);
		String[] n = f.list();
		System.out.println("len"+n.length);
	    for (int i=0; i<n.length; i++){
	    	File child = new File(PATH+File.separator+n[i]);
	        System.out.println(child.getName());
	        if(!child.isDirectory()){
	        	fileMap.put(child.getName(), child);
	        }
	    }
	}
	
	public File getFileByName(String name) {
		return fileMap.get(name);
	}
}
