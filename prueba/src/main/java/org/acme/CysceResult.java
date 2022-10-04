package org.acme;

public class CysceResult {

	public String code;
	public String description;
	
	public CysceResult(String cod, String desc) {
		this.code = cod;
		this.description = desc;
		// TODO Auto-generated constructor stub
	}	
	
	public String toString(){
		String resp = "{";
		if (code != null) {
			resp += "\"code\":\"" + code + "\", "; 
		}
		
		if (description != null) {
			resp += "\"description\":\"" + description + "\""; 
		}
		
		resp += "}"; 
		return resp;
	}

}