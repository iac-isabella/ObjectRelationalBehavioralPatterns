package br.edu.ifes.les.pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
	List<Entidade> clean;
	List<Entidade> changed;
	
	public UnitOfWork(){
		clean = new ArrayList<Entidade>();
		changed = new ArrayList<Entidade>();
	}
	
	public void add(Entidade entidade) {
        int index = clean.indexOf(entidade);
        if(index < 0){
        	clean.add(entidade);
        } else {
			clean.add(index, entidade);
		}
    }
	
	public void load(Entidade entidade) throws SQLException{
        int index = changed.indexOf(entidade);
        if(index < 0){
        	changed.add(entidade.load());
        } else {
			changed.add(index, entidade.load());
		}
    }
	
	public void commit() throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:sample.db");
        con.setAutoCommit(false);
		for(Entidade entidade : changed){
			entidade.update(con);
		}
		for(Entidade entidade : clean){
			entidade.createTable(con);
			entidade.insert(con);
		}
		con.commit();  
    }
	
}
