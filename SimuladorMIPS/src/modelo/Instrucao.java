package modelo;

public class Instrucao {
	protected int enderecoMemoria;
	protected int enderecoProximaInstrucao;

	public int getEnderecoProximaInstrucao() {
		return enderecoProximaInstrucao;
	}

	public void setEnderecoProximaInstrucao(int enderecoProximaInstrucao) {
		this.enderecoProximaInstrucao = enderecoProximaInstrucao;
	}

	public int getEnderecoMemoria() {
		return enderecoMemoria;
	}

	public void setEnderecoMemoria(int enderecoMemoria) {
		this.enderecoMemoria = enderecoMemoria;
	}
}
