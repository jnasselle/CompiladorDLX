import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class TablaSimbolo {
	private Map<String, ArrayList<Object>> mapaTablaSimbolos;
	private Integer posicionMemoria;
	
	public TablaSimbolo() {
		mapaTablaSimbolos = new HashMap<String, ArrayList<Object>>();
		posicionMemoria = 0;
	}
	
	public boolean agregarSimbolo(String nombreVar, String tipoDato) {
		if(!this.mapaTablaSimbolos.containsKey(nombreVar)) {
			ArrayList<Object> arrayListTmp = new ArrayList<Object>();
			arrayListTmp.add(tipoDato);
			arrayListTmp.add(posicionMemoria);
			this.mapaTablaSimbolos.put(nombreVar, arrayListTmp);
			switch(tipoDato) {
				case "int":
				case "unsigned int":
					posicionMemoria += 4;
					break;
				case "char":
				case "unsigned char":
					posicionMemoria += 1;
					break;
				case "short":
				case "unsigned short":
					posicionMemoria += 2;
					break;
			}
			System.out.println("El simbolo '" + nombreVar + "' fue agregado.");
			return true;
		}
		System.out.println("El simbolo '" + nombreVar + "' no fue agregado.");
		return false;
	}
	
	public String getTipoDato(String nombreVar) {
		return (String)this.mapaTablaSimbolos.get(nombreVar).get(0);
	}
	
	public Integer getPosicionMemoria(String nombreVar) {
		return (Integer)this.mapaTablaSimbolos.get(nombreVar).get(1);
	}
	
	public boolean existeNombreVar(String nombreVar) {
		return this.mapaTablaSimbolos.containsKey(nombreVar);
	}
}
