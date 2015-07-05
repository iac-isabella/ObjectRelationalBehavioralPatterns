package br.edu.ifes.les.pattern;

import br.edu.ifes.les.model.Livro;
import br.edu.ifes.les.persistence.LivroMapper;

public class ValueHolder {
	
    private int id;
    private Livro relatedEntity;
	 
	    public ValueHolder(int id) {
	        this.id = id;
	    }
	 
	    public Livro getRelatedEntity() throws Exception {
	        if (relatedEntity == null	){
	        	LivroMapper mapper = new LivroMapper();
				relatedEntity = mapper.getLivro(id);
	        }
	        return relatedEntity;
	    }
}
