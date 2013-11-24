package modelo;

public class J extends Instrucao {
	private int op;
	private int endereco;
	private String nomeVariavel;
	
	public J() {
		this.op = 2;
	}
	
	public J(int endereco) {
		this.endereco = endereco;
	}
	
	public int getOp() {
		return op;
	}
	public int getEndereco() {
		return endereco;
	}
	public void setOp(int op) {
		this.op = op;
	}
	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	public String getNomeVariavel() {
		return nomeVariavel;
	}

	public void setNomeVariavel(String nomeVariavel) {
		this.nomeVariavel = nomeVariavel;
	}
}
