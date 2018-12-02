package service.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dao.Role;
import entity.RoleEntity;
import service.RoleService;

import org.json.*;

public class RoleJsonService extends RoleService {
	String PATH = "resource/json";
	public Role findById(long id) {
		try {
			File f = new File(PATH+"/role.txt");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(f), "UTF-8");
			 BufferedReader br = new BufferedReader(isr);
			  String st; 
			  while ((st = br.readLine()) != null) {
				  JSONObject j = new JSONObject(st);
				  long jsonOb = j.getLong("id");
				  if(jsonOb == id) {
					  System.out.println(j.getString("name")); 
					  break;
				  }
			  } 
			return null;
		}catch(Exception e) {
			return  null;
		}
	}
	
	@Override
	public long createRole(RoleEntity roleEntity){
		try {
			File f = new File(PATH+"/role.txt");
			if(!f.exists()) {
				f.createNewFile();
			}
			String json = EntityToJSON(roleEntity);
			List<String> lines = Arrays.asList(json);
			Path file = Paths.get(PATH+"/role.txt");
			Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
			//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
			return 0;
		}catch(Exception e) {
			return  -1;
		}
	}
	
	private String EntityToJSON(RoleEntity roleEntity) {
		Role role = EntityToDAO(roleEntity);
		JSONObject j = new JSONObject(role);
		
		return j.toString();
	}
}
