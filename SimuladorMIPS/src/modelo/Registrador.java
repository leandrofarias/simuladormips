package modelo;

public class Registrador {
	private String nome;
	private int numero;
	private int valor;
	
	public Registrador() {}
	
	public Registrador(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getId() {
		return numero;
	}

	public void setId(int id) {
		this.numero = id;
	}
	
	
}
