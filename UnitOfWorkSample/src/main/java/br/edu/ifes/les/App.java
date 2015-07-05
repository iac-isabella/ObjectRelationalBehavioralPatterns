package br.edu.ifes.les;

import java.util.Random;

import br.edu.ifes.les.model.Autor;
import br.edu.ifes.les.model.Livro;
import br.edu.ifes.les.pattern.UnitOfWork;

public class App {

	public static void main(String[] args) {
		Random random = new Random();
		UnitOfWork UOW = new UnitOfWork();
		Autor autor = new Autor(random.nextInt(), "Obi Wan");
		UOW.add(autor);
		Livro livro = new Livro(random.nextInt(), "May the force be with you", 2015, autor);
		UOW.add(livro);
		try {
			UOW.commit();
		} catch (Exception e) {
			System.err.println("Erro ao persistir dados no banco!");
			e.printStackTrace();
		}
	}

}
