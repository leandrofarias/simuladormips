package modelo;

public class Beq extends Instrucao {
	private int op;
	private Registrador rs;
	private Registrador rt;
	private int endereco;
	
	public Beq() {
		this.op = 4;
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

	public Registrador getRs() {
		return rs;
	}

	public void setRs(Registrador rs) {
		this.rs = rs;
	}

	public Registrador getRt() {
		return rt;
	}

	public void setRt(Registrador rt) {
		this.rt = rt;
	}
}
