package modelo;

public class J extends Instrucao {
	private int op;
	private int endereco;
	
	public J() {}
	
	public J(int endereco) {
		this.op = 2;
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
}
