package org.beetl.corssmvc;

public class DomainBaseConfig {
	private String baseValue="";
	private String basePath="";
	private String baseModelPath="";
	private String name = "";
	public String getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(String baseValue) {
		this.baseValue = baseValue;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getBaseModelPath() {
		return baseModelPath;
	}
	public void setBaseModelPath(String baseModelPath) {
		this.baseModelPath = baseModelPath;
	}
	
	
	
}
