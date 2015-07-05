package br.edu.ifes.les.pattern;

import br.edu.ifes.les.model.Livro;
import br.edu.ifes.les.persistence.LivroMapper;

public class LivroGhost {
	
	private enum Status { GHOST, LOADED };
    private Status status;
    private int id;
    private Livro relatedEntity;
	 
	    public LivroGhost(int id) {
	    	this.status = Status.GHOST;
	        this.id = id;
	    }
	 
	    public Livro getRelatedEntity() {
	        if (status == Status.GHOST){
	        	LivroMapper mapper = new LivroMapper();
	        	try {
					relatedEntity = mapper.getLivro(id);
				} catch (Exception e) {
					relatedEntity = new Livro();
				} finally {
					status = Status.LOADED;
				}
	        }
	        return relatedEntity;
	    }
}
