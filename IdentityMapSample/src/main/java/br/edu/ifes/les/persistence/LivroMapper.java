package br.edu.ifes.les.persistence;

import java.sql.*;

import br.edu.ifes.les.model.Livro;
import br.edu.ifes.les.pattern.IdentityMap;

public class LivroMapper {
	
	private Connection con;

	/*
	 * Abre conexao com o SQLite.
	 */
    private void openConnection() {
        con = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.err.println("Excecao na funcao openConnection: classe nao encontrada org.sqlite.JDBC");
        }
        try {	
            con = DriverManager.getConnection("jdbc:sqlite:sample.db");
        } catch (SQLException ex) {
            System.err.println("Excecao na funcao openConnection: erro ao conectar ao banco.");
        }
    }
    
    /*
     * Fecha conexao com o SQLite.
     */
    private void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println("Excecao na funcao closeConnection.");
        }
    }
    
    /*
     * Executa uma query sem retorno.
     */
    private void execute(String query) {
        this.openConnection();
        Statement stmt;
        try {
            stmt = this.con.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            this.closeConnection();
        } catch (SQLException ex) {
        	System.err.println("Excecao na funcao execute.");
            ex.printStackTrace();
        }
    }
    
    /*
     * Contrutor que cria a tabela de livros no banco.
     */
	public LivroMapper() {
		this.openConnection();
        String sql = "create table if not exists livros"
                + "(id integer primary key,"
                + " titulo text not null,"
                + " ano integer not null,"
                + " autor text not null)";
        //System.out.println(sql);
        this.execute(sql);
        this.closeConnection();
	}
	
	/*
	 * Recupera um livro do banco.
	 */
	private Livro find(int key) throws Exception{
		this.openConnection();
		Statement stmt = this.con.createStatement();
		String query = "select * from livros where id = " + key;
		//System.out.println(query);
		ResultSet rs = stmt.executeQuery(query);
		Livro livro;
		if(rs.next()){
			livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getInt("ano"), rs.getString("autor"));
			rs.close();
			con.close();
			System.out.println("Livro obtido do banco.");
			return livro;
		}
		else{
			throw new Exception("Livro não encontrado.");
		}
    }
	
	/*
	 * Retorna um livro de acordo com a chave solicitada.
	 */
	public Livro getLivro(int key) throws Exception {
		Livro livro = IdentityMap.isInto(key);
		if(livro == null) {
	      livro = find(key);
	      IdentityMap.add(livro);
	    }
	    return livro;
	}
	
	public void save(Livro livro){
		String sql = "";
		try {
			find(livro.getID());
			sql = "update livros set titulo = '" 
					+ livro.getTitulo() 
					+ "', ano = " + livro.getAno() 
					+ ", autor = '" + livro.getAutor() + "'";
			//System.out.println(sql);
			execute(sql);
			
		} catch (Exception e) {
			sql = "insert into livros values ("
					+ livro.getID() + ", '"
					+ livro.getTitulo() 
					+ "', " + livro.getAno() 
					+ ", '" + livro.getAutor() + "')";
			//System.out.println(sql);
			execute(sql);
		}
	}
	
}