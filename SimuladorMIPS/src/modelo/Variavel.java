package modelo;

import java.util.ArrayList;
import java.util.List;

public class Variavel {
	private String nome;
	private List<Integer> valores;
	
	public Variavel() {
		this.valores = new ArrayList<Integer>();
	}
	
	public void adicionarValor(int valor) {
		valores.add(valor);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Integer> getValores() {
		return valores;
	}

	public void setValores(List<Integer> valores) {
		this.valores = valores;
	}

}
