package util;

import java.util.HashMap;

import modelo.Registrador;

public class BancoRegistradores {
	private HashMap<Integer, Registrador> bancoRegistradores;
	
	public BancoRegistradores() {
		this.bancoRegistradores = new HashMap<Integer, Registrador>();
	}
	
	public void adicionarRegistrador(Registrador registrador) {
		if (!this.bancoRegistradores.containsKey(registrador.getNumero()))
				this.bancoRegistradores.put(registrador.getNumero(), registrador);
	}
	
	public Registrador getRegistrador(int numero) {
		if (this.bancoRegistradores.containsKey(numero)) {
			return this.bancoRegistradores.get(numero);
		}
		
		return null;
	}

}
