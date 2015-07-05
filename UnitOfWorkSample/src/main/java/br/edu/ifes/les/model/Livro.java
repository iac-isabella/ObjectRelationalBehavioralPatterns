package br.edu.ifes.les.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifes.les.pattern.Entidade;

public class Livro extends Entidade {
	
	private String titulo;
	private int ano;
	private Autor autor;
	
	public Livro(int iD, String titulo, int ano, Autor autor) {
		super(iD);
		this.titulo = titulo;
		this.ano = ano;
		this.autor = autor;
	}

	@Override
	public void createTable(Connection connection) throws SQLException {
		String sql = "create table if not exists livros"
	            + "(id integer primary key,"
	            + " titulo text not null,"
	            + " ano integer not null,"
	            + " autor integer not null);";
		PreparedStatement query = connection.prepareStatement(sql);
        query.execute();
	}

	@Override
	public void insert(Connection connection) throws SQLException {
		String sql = "insert into livros values (?, ?, ?, ?);";
		PreparedStatement query = connection.prepareStatement(sql);
		query.setInt(1, ID);
		query.setString(2, titulo);
		query.setInt(3, ano);
		query.setInt(4, autor.getID());
		query.execute();
	}

	@Override
	public void update(Connection connection) throws SQLException {
		String sql = "update livros set titulo = ?, ano= ?, autor= ? where id = ? ;";
		PreparedStatement query = connection.prepareStatement(sql);
		query.setString(1, titulo);
		query.setInt(2, ano);
		query.setInt(3, autor.getID());
		query.setInt(4, ID);
		query.execute();
	}

	@Override
	public Entidade load() {
		return this;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
}
