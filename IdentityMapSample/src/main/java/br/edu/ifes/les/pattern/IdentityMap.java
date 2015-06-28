package br.edu.ifes.les.pattern;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifes.les.model.Livro;

public class IdentityMap {
	
	private static Map<Integer, Livro> map = new HashMap<Integer, Livro>();
	
	public static Livro isInto(int key) throws Exception { 
		Livro objeto = map.get(new Integer (key));
		return objeto;		
	}
 
	public static void add(Livro objeto) {
		map.put(new Integer(objeto.getID()), objeto);
	}
	
}
