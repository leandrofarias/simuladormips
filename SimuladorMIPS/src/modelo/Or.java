package modelo;

public class Or extends Instrucao {
	private int op;
	private Registrador rs;
	private Registrador rt;
	private Registrador rd;
	private int shamt;
	private int funct;
	
	public Or() {
		this.op = 0;
		this.shamt = 0;
		this.funct = 37;
	}

	public int getOp() {
		return op;
	}

	public int getShamt() {
		return shamt;
	}

	public int getFunct() {
		return funct;
	}

	public void setOp(int op) {
		this.op = op;
	}

	public void setShamt(int shamt) {
		this.shamt = shamt;
	}

	public void setFunct(int funct) {
		this.funct = funct;
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

	public Registrador getRd() {
		return rd;
	}

	public void setRd(Registrador rd) {
		this.rd = rd;
	}
}
