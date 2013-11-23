package modelo;

public class Add extends Instrucao {
	private int op;
	private int rs;
	private int rt;
	private int rd;
	private int shamt;
	private int funct;
	
	public Add() {}
	
	public Add(int rs, int rt, int rd) {
		this.op = 0;
		this.rs = rs;
		this.rt = rt;
		this.rd = rd;
		this.shamt = 0;
		this.funct = 32;
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

	public int getRd() {
		return rd;
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

	public void setRs(int rs) {
		this.rs = rs;
	}

	public void setRt(int rt) {
		this.rt = rt;
	}

	public void setRd(int rd) {
		this.rd = rd;
	}

	public void setShamt(int shamt) {
		this.shamt = shamt;
	}

	public void setFunct(int funct) {
		this.funct = funct;
	}

	
}
