package util;

public class SinalControle {
	
	private String valor;
	private String nome;
	private boolean ativo;
	
	public SinalControle( String nome ) {
		
		this.valor = "";
		this.nome = nome;
		this.ativo = false;
		
	}
	
	public void modificarValor( String valor ) {
		
		this.valor = valor;
		this.ativo = true;
		
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
