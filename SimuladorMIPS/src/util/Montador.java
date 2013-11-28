package util;

import java.io.File;
import java.io.FileNotFoundException;
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
	private Scanner entrada;
	
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
				registrador = new Registrador(nomeRegistrador, 0);
				registrador.setValor(0);
				break;
			case "$v0":
				registrador = new Registrador(nomeRegistrador, 2);
				break;
			case "$v1":
				registrador = new Registrador(nomeRegistrador, 3);
				break;
			case "$a0":
				registrador = new Registrador(nomeRegistrador, 4);
				break;
			case "$a1":
				registrador = new Registrador(nomeRegistrador, 5);
				break;
			case "$a2":
				registrador = new Registrador(nomeRegistrador, 6);
				break;
			case "$a3":
				registrador = new Registrador(nomeRegistrador, 7);
				break;
			case "$t0":
				registrador = new Registrador(nomeRegistrador, 8);
				break;
			case "$t1":
				registrador = new Registrador(nomeRegistrador, 9);
				break;
			case "$t2":
				registrador = new Registrador(nomeRegistrador, 10);
				break;
			case "$t3":
				registrador = new Registrador(nomeRegistrador, 11);
				break;
			case "$t4":
				registrador = new Registrador(nomeRegistrador, 12);
				break;
			case "$t5":
				registrador = new Registrador(nomeRegistrador, 13);
				break;
			case "$t6":
				registrador = new Registrador(nomeRegistrador, 14);
				break;
			case "$t7":
				registrador = new Registrador(nomeRegistrador, 15);
				break;
			case "$s0":
				registrador = new Registrador(nomeRegistrador, 16);
				break;
			case "$s1":
				registrador = new Registrador(nomeRegistrador, 17);
				break;
			case "$s2":
				registrador = new Registrador(nomeRegistrador, 18);
				break;
			case "$s3":
				registrador = new Registrador(nomeRegistrador, 19);
				break;
			case "$s4":
				registrador = new Registrador(nomeRegistrador, 20);
				break;
			case "$s5":
				registrador = new Registrador(nomeRegistrador, 21);
				break;
			case "$s6":
				registrador = new Registrador(nomeRegistrador, 22);
				break;
			case "$s7":
				registrador = new Registrador(nomeRegistrador, 23);
				break;
			case "$t8":
				registrador = new Registrador(nomeRegistrador, 24);
				break;
			case "$t9":
				registrador = new Registrador(nomeRegistrador, 25);
				break;
			case "$gp":
				registrador = new Registrador(nomeRegistrador, 28);
				break;
			case "$sp":
				registrador = new Registrador(nomeRegistrador, 29);
				break;
			case "$fp":
				registrador = new Registrador(nomeRegistrador, 30);
				break;
			case "$ra":
				registrador = new Registrador(nomeRegistrador, 31);
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
		
		
		int enderecoMemoria = 0;
		boolean encontreiMarcador = false;
		int enderecoMarcador = -1;
		Instrucao instrucaoAnterior = null, ultimaInstrucao = null;
		
		if (entrada.next().equals(".globl") && entrada.next().equals("main") && entrada.next().equals("main:")) {
			while (entrada.hasNext()) {
				String operacao = entrada.next();
				if (!operacao.endsWith(":")) {
					Instrucao instrucao = this.getInstrucao(operacao);
					
					if (encontreiMarcador) {
						instrucao.setEnderecoMemoria(enderecoMarcador);
						encontreiMarcador = false;
					} else {
						instrucao.setEnderecoMemoria(enderecoMemoria);
						enderecoMemoria += 4;
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
								((Beq) instrucao).setEndereco(Integer.parseInt(reg3));
								
								bancoRegistradores.adicionarRegistrador(r1);
								bancoRegistradores.adicionarRegistrador(r2);
							}
						} else {
							StringTokenizer str2 = new StringTokenizer(reg2);
							String nomeVariavel = str2.nextToken("(");
							String rs = str2.nextToken(")").substring(1);
							
							if (instrucao instanceof Lw) {
								Registrador r1 = getRegistrador(reg1);
								Registrador r2 = getRegistrador(rs);
								
								((Lw) instrucao).setRt(r1);
								((Lw) instrucao).setRs(r2);
								((Lw) instrucao).setNomeVariavel(nomeVariavel);
								
								bancoRegistradores.adicionarRegistrador(r1);
								bancoRegistradores.adicionarRegistrador(r2);
							} else if (instrucao instanceof Sw) {
								Registrador r1 = getRegistrador(reg1);
								Registrador r2 = getRegistrador(rs);
								
								((Sw) instrucao).setRt(r1);
								((Sw) instrucao).setRs(r2);
								((Sw) instrucao).setNomeVariavel(nomeVariavel);
								
								bancoRegistradores.adicionarRegistrador(r1);
								bancoRegistradores.adicionarRegistrador(r2);
							}
						}
					} else {
						if (instrucao instanceof J) {
							((J) instrucao).setEndereco(Integer.parseInt(entrada.next()));
						}
					}

					//add na memoria de instrucoes
					
					if (instrucaoAnterior == null) {
						instrucaoAnterior = instrucao;
					} else {
						instrucaoAnterior.setEnderecoProximaInstrucao(instrucao.getEnderecoMemoria());
						memoriaInstrucoes.adicionarInstrucao(instrucaoAnterior);
						instrucaoAnterior = instrucao;
						ultimaInstrucao = instrucao;
					}
				} else {
					StringTokenizer strMarcador = new StringTokenizer(operacao);
					enderecoMarcador = Integer.parseInt(strMarcador.nextToken(":"));
					encontreiMarcador = true;
				}
			}
			
			ultimaInstrucao.setEnderecoProximaInstrucao(-1);
			memoriaInstrucoes.adicionarInstrucao(ultimaInstrucao);
		}
	}
	
	public void fecharArquivo() {
		if (entrada != null) {
			entrada.close();
		}
	}
	
	public void montar(String nomeArquivo, List<Variavel> listaVariaveis, MemoriaInstrucoes memoriaInstrucoes, BancoRegistradores bancoRegistradores) {
		this.abrirArquivo(nomeArquivo);
		this.lerDados(listaVariaveis, memoriaInstrucoes, bancoRegistradores);
		this.fecharArquivo();
	}
}
