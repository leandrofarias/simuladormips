package modelo;

public class Sw extends Instrucao {
	private int op;
	private Registrador rs;
	private Registrador rt;
	private int endereco;
	private String nomeVariavel;
	
	public Sw() {
		this.op = 43;
	}

	public int getOp() {
		return op;
	}

	public Registrador getRs() {
		return rs;
	}

	public Registrador getRt() {
		return rt;
	}

	public int getEndereco() {
		return endereco;
	}

	public void setOp(int op) {
		this.op = op;
	}

	public void setRs(Registrador rs) {
		this.rs = rs;
	}

	public void setRt(Registrador rt) {
		this.rt = rt;
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
