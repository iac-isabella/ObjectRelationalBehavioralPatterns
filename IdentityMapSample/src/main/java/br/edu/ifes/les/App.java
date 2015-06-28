package br.edu.ifes.les;

import java.util.Random;

import br.edu.ifes.les.model.Livro;
import br.edu.ifes.les.persistence.LivroMapper;

public class App {

	public static void main(String[] args) {
		Random random = new Random();
		int id = random.nextInt();
		Livro livro = new Livro(id, "May the force be with you", 2015, "Obi Wan");
		System.out.println("Livro antes de salvar no banco...");
		System.out.println(livro.toString());
		LivroMapper mapper = new LivroMapper();
		mapper.save(livro);
		try {
			System.out.println("Solicitando o livro...");
			Livro livroBanco = mapper.getLivro(id);
			System.out.println(livroBanco.toString());
		} catch (Exception e) {
			System.err.println("Erro ao recuperar o livro :'(");
			e.printStackTrace();
		}
		try {
			System.out.println("Solicitando o livro novamente...");
			Livro livroMap = mapper.getLivro(id);
			System.out.println(livroMap.toString());
		} catch (Exception e) {
			System.err.println("Erro ao recuperar o livro :'(");
			e.printStackTrace();
		}
	}

}
