package util;

import java.util.HashMap;

import modelo.Instrucao;

public class MemoriaInstrucoes {
	private HashMap<Integer, Instrucao> instrucoes;
	
	public MemoriaInstrucoes() {
		this.instrucoes = new HashMap<Integer, Instrucao>();
	}
	
	public void adicionarInstrucao(Instrucao instrucao) {
		this.instrucoes.put(instrucao.getEnderecoMemoria(), instrucao);
	}
	
	public Instrucao getInstrucao(int endereco) {
		if (this.instrucoes.containsKey(endereco)) {
			return this.instrucoes.get(endereco);
		}
		
		return null;
	}

	public HashMap<Integer, Instrucao> getInstrucoes() {
		return instrucoes;
	}

	public void setInstrucoes(HashMap<Integer, Instrucao> instrucoes) {
		this.instrucoes = instrucoes;
	}
}
