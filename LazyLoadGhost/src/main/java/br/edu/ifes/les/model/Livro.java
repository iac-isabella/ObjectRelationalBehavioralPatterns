package br.edu.ifes.les.model;

public class Livro {
	
	private int ID;
	private String titulo;
	private int ano;
	private String autor;
	
	public Livro() {
		super();
	}
	
	public Livro(int iD, String titulo, int ano, String autor) {
		super();
		ID = iD;
		this.titulo = titulo;
		this.ano = ano;
		this.autor = autor;
	}
	
	public String toString(){
		return "LIVRO: #Titulo: " + titulo + " #Ano: " + ano + " #Autor: " + autor;
	}

	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
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
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
}
