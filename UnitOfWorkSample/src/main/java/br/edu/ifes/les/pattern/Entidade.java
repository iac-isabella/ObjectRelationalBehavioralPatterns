package br.edu.ifes.les.pattern;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Entidade {
	
	protected int ID;

	public Entidade(int iD) {
		super();
		this.ID = iD;
	}
	
	public abstract void createTable(Connection connection) throws SQLException;
	
	public abstract void insert(Connection connection) throws SQLException;
	
	public abstract void update(Connection connection) throws SQLException;
	
	public abstract Entidade load();

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
}
