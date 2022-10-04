package org.acme;


public class Persona {

    Integer id;
    String nombre;

    public void setPersona (Integer id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public String toString(){
		String resp = "{";
		if (this.id != null) {
			resp += "\"id\":\"" + this.id + "\", "; 
		}
		
		if (this.nombre != null) {
			resp += "\"nombre\":\"" + this.nombre + "\""; 
		}
		
		resp += "}"; 
		return resp;
	}

}