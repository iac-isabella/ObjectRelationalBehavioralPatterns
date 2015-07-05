package br.edu.ifes.les.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifes.les.pattern.Entidade;

public class Autor extends Entidade {
	
	private String nome;
	
	public Autor(int iD, String name) {
		super(iD);
		this.nome = name;
	}
	
	@Override
	public void createTable(Connection connection) throws SQLException {
		String sql = "create table if not exists autores"
	            + "(id integer primary key,"
	            + " nome text not null);";
		PreparedStatement query = connection.prepareStatement(sql);
        query.execute();
	}
	
	@Override
	public void insert(Connection connection) throws SQLException {
		String sql = "insert into autores values (?, ?);";
		PreparedStatement query = connection.prepareStatement(sql);
		query.setInt(1, ID);
		query.setString(2, nome);
		query.execute();
	}

	@Override
	public void update(Connection connection) throws SQLException {
		String sql = "update autores set nome = ?;";
		PreparedStatement query = connection.prepareStatement(sql);
		query.setString(1, nome);
		query.setInt(2, ID);
		query.execute();
	}

	@Override
	public Entidade load() {
		return this;
	}
	
	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}
	
}
