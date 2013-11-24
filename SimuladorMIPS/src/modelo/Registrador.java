package modelo;

public class Registrador {
	private int id;
	private String nome;
	private int numero;
	private int valor;
	
	public Registrador() {}
	
	public Registrador(int id, String nome, int numero) {
		this.id = id;
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
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
