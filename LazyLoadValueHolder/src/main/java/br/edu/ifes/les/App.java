package br.edu.ifes.les;


import java.util.Random;

import br.edu.ifes.les.model.Livro;
import br.edu.ifes.les.pattern.ValueHolder;
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
		System.out.println("Criando value holder...");
		ValueHolder valueHolder = new ValueHolder(id);
		System.out.println("Obtendo livro do value holder...");
		String descricaoLivro;
		try {
			descricaoLivro = valueHolder.getRelatedEntity().toString();
			System.out.println(descricaoLivro);
		} catch (Exception e) {
			System.err.println("Erro ao recuperar livro do value holder :/");
		}
	}

}
