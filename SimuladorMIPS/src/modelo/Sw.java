package modelo;

public class Sw extends Instrucao {
	private int op;
	private int rs;
	private int rt;
	private int endereco;
	
	public Sw() {}
	
	public Sw(int rs, int rt, int endereco) {
		this.op = 43;
		this.rs = rs;
		this.rt = rt;
		this.endereco = endereco;
	}

	public int getOp() {
		return op;
	}

	public int getRs() {
		return rs;
	}

	public int getRt() {
		return rt;
	}

	public int getEndereco() {
		return endereco;
	}

	public void setOp(int op) {
		this.op = op;
	}

	public void setRs(int rs) {
		this.rs = rs;
	}

	public void setRt(int rt) {
		this.rt = rt;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}
}
