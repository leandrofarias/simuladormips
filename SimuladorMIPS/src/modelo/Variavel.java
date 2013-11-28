package modelo;

import java.util.ArrayList;


public class Variavel {
	private String nome;
	private ArrayList<Integer> valores;
	
	public Variavel() {
		this.valores = new ArrayList<Integer>();
	}
	
	public void adicionarValor(int valor) {
		this.valores.add(valor);
	}
	
	public void setValor( int novoValor, int id ) {
		this.valores.set( id, novoValor );
	}
	
	public int getValor( int posicaoMemoria ) {
		return this.valores.get( posicaoMemoria );
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
