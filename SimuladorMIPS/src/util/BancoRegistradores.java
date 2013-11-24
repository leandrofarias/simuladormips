package util;

import java.util.HashMap;

import modelo.Registrador;

public class BancoRegistradores {
	private HashMap<Integer, Registrador> bancoRegistradores;
	
	public BancoRegistradores() {
		this.bancoRegistradores = new HashMap<Integer, Registrador>();
	}
	
	public void adicionarRegistrador(Registrador registrador) {
		this.bancoRegistradores.put(registrador.getId(), registrador);
	}
	
	public Registrador getRegistrador(int id) {
		if (this.bancoRegistradores.containsKey(id)) {
			return this.bancoRegistradores.get(id);
		}
		
		return null;
	}

}
