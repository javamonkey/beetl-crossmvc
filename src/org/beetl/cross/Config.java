package org.beetl.cross;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	Properties ps = new Properties();
	public Config(String path) throws IOException{
		ps.load(new FileInputStream(new File(path)));
	}
	
	public String get(String key){
		return ps.getProperty(key);
	}
	
	public int getInt(String key,int value){
		String str = ps.getProperty(key);
		if(str==null)return value;
		else return Integer.parseInt(str);
		
	}
	
	public String get(String key,String defaulPath){
		String str = ps.getProperty(key);
		if(str==null)return defaulPath;
		else return str;
	}
	
}
