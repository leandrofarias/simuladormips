package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import modelo.Add;
import modelo.And;
import modelo.Beq;
import modelo.Instrucao;
import modelo.J;
import modelo.Lw;
import modelo.Or;
import modelo.Registrador;
import modelo.Sub;
import modelo.Sw;
import modelo.Variavel;

public class Montador {
	private int idRegistrador;
	private Scanner entrada;
	
	public Montador() {
		this.idRegistrador = 1;
	}
	
	public Instrucao getInstrucao(String operacao) {
		Instrucao instrucao = null;
		switch (operacao) {
			case "add":
				instrucao = new Add();
				break;
			case "sub":
				instrucao = new Sub();
				break;
			case "and":
				instrucao = new And();
				break;
			case "or":
				instrucao = new Or();
				break;
			case "lw":
				instrucao = new Lw();
				break;
			case "sw":
				instrucao = new Sw();
				break;
			case "j":
				instrucao = new J();
				break;
			case "beq":
				instrucao = new Beq();
				break;
		}
		
		return instrucao;
	}
	
	public Registrador getRegistrador(String nomeRegistrador) {
		Registrador registrador = null;
		
		switch (nomeRegistrador) {
			case "$zero":
				registrador = new Registrador(getIdRegistrador(),nomeRegistrador, 0);
				atualizarIdRegistrador();
				break;
			case "$v0":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 2);
				atualizarIdRegistrador();
				break;
			case "$v1":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 3);
				atualizarIdRegistrador();
				break;
			case "$a0":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 4);
				atualizarIdRegistrador();
				break;
			case "$a1":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 5);
				atualizarIdRegistrador();
				break;
			case "$a2":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 6);
				atualizarIdRegistrador();
				break;
			case "$a3":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 7);
				atualizarIdRegistrador();
				break;
			case "$t0":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 8);
				atualizarIdRegistrador();
				break;
			case "$t1":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 9);
				atualizarIdRegistrador();
				break;
			case "$t2":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 10);
				atualizarIdRegistrador();
				break;
			case "$t3":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 11);
				atualizarIdRegistrador();
				break;
			case "$t4":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 12);
				atualizarIdRegistrador();
				break;
			case "$t5":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 13);
				atualizarIdRegistrador();
				break;
			case "$t6":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 14);
				atualizarIdRegistrador();
				break;
			case "$t7":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 15);
				atualizarIdRegistrador();
				break;
			case "$s0":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 16);
				atualizarIdRegistrador();
				break;
			case "$s1":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 17);
				atualizarIdRegistrador();
				break;
			case "$s2":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 18);
				atualizarIdRegistrador();
				break;
			case "$s3":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 19);
				atualizarIdRegistrador();
				break;
			case "$s4":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 20);
				atualizarIdRegistrador();
				break;
			case "$s5":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 21);
				atualizarIdRegistrador();
				break;
			case "$s6":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 22);
				atualizarIdRegistrador();
				break;
			case "$s7":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 23);
				atualizarIdRegistrador();
				break;
			case "$t8":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 24);
				atualizarIdRegistrador();
				break;
			case "$t9":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 25);
				atualizarIdRegistrador();
				break;
			case "$gp":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 28);
				atualizarIdRegistrador();
				break;
			case "$sp":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 29);
				atualizarIdRegistrador();
				break;
			case "$fp":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 30);
				atualizarIdRegistrador();
				break;
			case "$ra":
				registrador = new Registrador(getIdRegistrador(), nomeRegistrador, 31);
				atualizarIdRegistrador();
				break;
		}
		
		return registrador;
	}
	
	public void abrirArquivo(String nomeArquivo) {
		try {
			entrada = new Scanner(new File(nomeArquivo));
		} catch (FileNotFoundException e) {
			System.err.println( "Erro ao abrir o arquivo." );
	        System.exit(1);
		}
	}
	
	public void lerDados(List<Variavel> listaVariaveis, MemoriaInstrucoes memoriaInstrucoes, BancoRegistradores bancoRegistradores) {
		if (entrada.hasNext() && entrada.next().equals(".data")) {
			boolean areaDados = true;
			do {
				Variavel variavel = new Variavel();
				if (entrada.next().equals(".globl")) {
					variavel.setNome(entrada.next());
					if (entrada.next().equals(variavel.getNome() + ":") && entrada.next().equals(".word")) {
						String valores = entrada.next();
						if (valores.length() > 1) {
							StringTokenizer listaValores = new StringTokenizer(valores);
							while (listaValores.hasMoreTokens()) {
								variavel.adicionarValor(Integer.parseInt(listaValores.nextToken(",").trim()));
							}
						} else if (valores.length() == 1) {
							variavel.adicionarValor(Integer.parseInt(valores));	
						}
						listaVariaveis.add(variavel);
					} else {
						System.out.println("SINTAXE ERRADA!");
						areaDados = false;
					}
				} else {
					areaDados = false;
				}
			} while(areaDados);
		} else {
			System.out.println("SINTAXE ERRADA!");
		}
		
		List<String> listaMarcadores = new ArrayList<String>();
		int enderecoMemoria = 0;
		boolean encontreiMarcador = false;
		int instrucaoAposMarcador = -1;
		Instrucao instrucaoAnterior = null, ultimaInstrucao = null;
		
		if (entrada.next().equals(".globl") && entrada.next().equals("main") && entrada.next().equals("main:")) {
			while (entrada.hasNext()) {
				String operacao = entrada.next();
				if (!operacao.endsWith(":")) {
					Instrucao instrucao = this.getInstrucao(operacao);
					instrucao.setEnderecoMemoria(enderecoMemoria);
					enderecoMemoria += 4;
					
					if (encontreiMarcador) {
						instrucaoAposMarcador = instrucao.getEnderecoMemoria();
						encontreiMarcador = false;
					}
					
					if (!operacao.equals("j")) {
						StringTokenizer operacaoParte2 = new StringTokenizer(entrada.next());
						String reg1 = operacaoParte2.nextToken(",").trim();
						String reg2 = operacaoParte2.nextToken(",").trim();
						
						if (operacaoParte2.hasMoreTokens()) {
							String reg3 = operacaoParte2.nextToken(",").trim();
							if (instrucao instanceof Add) {
								Registrador r1 = getRegistrador(reg1);
								Registrador r2 = getRegistrador(reg2);
								Registrador r3 = getRegistrador(reg3);
								
								((Add) instrucao).setRd(r1);
								((Add) instrucao).setRs(r2);
								((Add) instrucao).setRt(r3);
								
								bancoRegistradores.adicionarRegistrador(r1);
								bancoRegistradores.adicionarRegistrador(r2);
								bancoRegistradores.adicionarRegistrador(r3);
							} else if (instrucao instanceof Sub) {
								Registrador r1 = getRegistrador(reg1);
								Registrador r2 = getRegistrador(reg2);
								Registrador r3 = getRegistrador(reg3);
								
								((Sub) instrucao).setRd(r1);
								((Sub) instrucao).setRs(r2);
								((Sub) instrucao).setRt(r3);
								
								bancoRegistradores.adicionarRegistrador(r1);
								bancoRegistradores.adicionarRegistrador(r2);
								bancoRegistradores.adicionarRegistrador(r3);
							} else if (instrucao instanceof And) {
								Registrador r1 = getRegistrador(reg1);
								Registrador r2 = getRegistrador(reg2);
								Registrador r3 = getRegistrador(reg3);
								
								((And) instrucao).setRd(r1);
								((And) instrucao).setRs(r2);
								((And) instrucao).setRt(r3);
								
								bancoRegistradores.adicionarRegistrador(r1);
								bancoRegistradores.adicionarRegistrador(r2);
								bancoRegistradores.adicionarRegistrador(r3);
							} else if (instrucao instanceof Or) {
								Registrador r1 = getRegistrador(reg1);
								Registrador r2 = getRegistrador(reg2);
								Registrador r3 = getRegistrador(reg3);
								
								((Or) instrucao).setRd(r1);
								((Or) instrucao).setRs(r2);
								((Or) instrucao).setRt(r3);
								
								bancoRegistradores.adicionarRegistrador(r1);
								bancoRegistradores.adicionarRegistrador(r2);
								bancoRegistradores.adicionarRegistrador(r3);
							} else if (instrucao instanceof Beq) {
								Registrador r1 = getRegistrador(reg1);
								Registrador r2 = getRegistrador(reg2);
								
								((Beq) instrucao).setRs(r1);
								((Beq) instrucao).setRt(r2);
								((Beq) instrucao).setNomeVariavel(reg3);
								
								bancoRegistradores.adicionarRegistrador(r1);
								bancoRegistradores.adicionarRegistrador(r2);
							}
						} else {
							if (instrucao instanceof Lw) {
								Registrador r1 = getRegistrador(reg1);
								
								((Lw) instrucao).setRt(r1);
								((Lw) instrucao).setNomeVariavel(reg2);
								
								bancoRegistradores.adicionarRegistrador(r1);
							} else if (instrucao instanceof Sw) {
								Registrador r1 = getRegistrador(reg1);
								
								((Sw) instrucao).setRt(r1);
								((Sw) instrucao).setNomeVariavel(reg2);
								
								bancoRegistradores.adicionarRegistrador(r1);
							}
						}
					} else {
						if (instrucao instanceof J) {
							((J) instrucao).setNomeVariavel(entrada.next());
							instrucao.setEnderecoProximaInstrucao(instrucaoAposMarcador);
						}
					}

					//add na memoria de instrucoes
					if (!(instrucao instanceof J)) {
						if (instrucaoAnterior == null) {
							instrucaoAnterior = instrucao;
						} else {
							instrucaoAnterior.setEnderecoProximaInstrucao(instrucao.getEnderecoMemoria());
							memoriaInstrucoes.adicionarInstrucao(instrucaoAnterior);
							instrucaoAnterior = instrucao;
							ultimaInstrucao = instrucao;
						}
					}
				} else {
					StringTokenizer marcador = new StringTokenizer(operacao);
					listaMarcadores.add(marcador.nextToken(":"));
					encontreiMarcador = true;
				}
			}
			
			memoriaInstrucoes.adicionarInstrucao(ultimaInstrucao);
		}
	}
	
	public void fecharArquivo() {
		if (entrada != null) {
			entrada.close();
		}
	}
	
	public int getIdRegistrador() {
		return idRegistrador;
	}

	public void atualizarIdRegistrador() {
		this.idRegistrador++;
	}
	
	public void montar(String nomeArquivo, List<Variavel> listaVariaveis, MemoriaInstrucoes memoriaInstrucoes, BancoRegistradores bancoRegistradores) {
		this.abrirArquivo(nomeArquivo);
		this.lerDados(listaVariaveis, memoriaInstrucoes, bancoRegistradores);
		this.fecharArquivo();
	}
	
	public static void main(String[] args) {
		Montador m = new Montador();
		List<Variavel> lista = new ArrayList<Variavel>();
		
		MemoriaInstrucoes memoriaInstrucoes = new MemoriaInstrucoes();
		BancoRegistradores bancoRegistradores = new BancoRegistradores();
		
		m.montar("teste.txt", lista, memoriaInstrucoes, bancoRegistradores);
		int count = 0;
		for (int i = 1; i <= memoriaInstrucoes.getSizeMemoria(); i++) {
			Instrucao ins = memoriaInstrucoes.getInstrucao(count);
			if (ins instanceof Add) {
				System.out.println("OpAdd: " + ((Add) ins).getOp());
				System.out.println("RegDAdd: " + ((Add) ins).getRd().getNome());
			} else if (ins instanceof Lw) {
				System.out.println("OpLw: " + ((Lw) ins).getOp());
			} else if (ins instanceof Sw) {
				System.out.println("OpSw: " + ((Sw) ins).getOp());
			} else if (ins instanceof Sub) {
				System.out.println("OpSub: " + ((Sub) ins).getOp());
				System.out.println("RegDSub: " + ((Sub) ins).getRd().getValor());
			}
			count += 4;
		}
	}
	
}
