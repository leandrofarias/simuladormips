package modelo;

public class Sub extends Instrucao {
	private int op;
	private Registrador rs;
	private Registrador rt;
	private Registrador rd;
	private int shamt;
	private int funct;
	
	public Sub() {
		this.op = 0;
		this.shamt = 0;
		this.funct = 34;
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

	public Registrador getRs() {
		return rs;
	}

	public Registrador getRt() {
		return rt;
	}

	public Registrador getRd() {
		return rd;
	}

	public void setRs(Registrador rs) {
		this.rs = rs;
	}

	public void setRt(Registrador rt) {
		this.rt = rt;
	}

	public void setRd(Registrador rd) {
		this.rd = rd;
	}

	public void setShamt(int shamt) {
		this.shamt = shamt;
	}

	public void setFunct(int funct) {
		this.funct = funct;
	}
}
