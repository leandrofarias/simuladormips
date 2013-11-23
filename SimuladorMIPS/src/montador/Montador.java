package montador;

import java.util.StringTokenizer;

public class Montador {
	public static void main(String[] args) {
		StringTokenizer instrucao = new StringTokenizer("add $t0,$s1,$s2");
		int numeroOperacao, numeroRegistrador1, numeroRegistrador2, numeroRegistrador3;
		String operacao, registrador1, registrador2, registrador3;
		operacao = instrucao.nextToken();
		
		Montador montador = new Montador();
		
		numeroOperacao = montador.getNumeroOperacao(operacao);
		System.out.println(numeroOperacao);
		
		registrador1 = instrucao.nextToken(",").trim();
		numeroRegistrador1 = montador.getNumeroRegistrador(registrador1);
		System.out.println(numeroRegistrador1);
		registrador2 = instrucao.nextToken(",").trim();
		numeroRegistrador2 = montador.getNumeroRegistrador(registrador2);
		System.out.println(numeroRegistrador2);
		registrador3 = instrucao.nextToken(",").trim();
		numeroRegistrador3 = montador.getNumeroRegistrador(registrador3);
		System.out.println(numeroRegistrador3);
	}
	
	public int getNumeroOperacao(String operacao) {
		int numeroOperacao = -1;
		
		switch (operacao) {
			case "add":
				numeroOperacao = 0;
				break;
			case "sub":
				numeroOperacao = 0;
				break;
			case "and":
				numeroOperacao = 0;
				break;
			case "or":
				numeroOperacao = 0;
				break;
			case "lw":
				numeroOperacao = 35;
				break;
			case "sw":
				numeroOperacao = 43;
				break;
			case "j":
				numeroOperacao = 2;
				break;
			case "beq":
				numeroOperacao = 4;
				break;
		}
		
		return numeroOperacao;
	}
	
	public int getNumeroRegistrador(String registrador) {
		int numeroRegistrador = -1;
		
		switch (registrador) {
			case "$zero":
				numeroRegistrador = 0;
				break;
			case "$v0":
				numeroRegistrador = 2;
				break;
			case "$v1":
				numeroRegistrador = 3;
				break;
			case "$a0":
				numeroRegistrador = 4;
				break;
			case "$a1":
				numeroRegistrador = 5;
				break;
			case "$a2":
				numeroRegistrador = 6;
				break;
			case "$a3":
				numeroRegistrador = 7;
				break;
			case "$t0":
				numeroRegistrador = 8;
				break;
			case "$t1":
				numeroRegistrador = 9;
				break;
			case "$t2":
				numeroRegistrador = 10;
				break;
			case "$t3":
				numeroRegistrador = 11;
				break;
			case "$t4":
				numeroRegistrador = 12;
				break;
			case "$t5":
				numeroRegistrador = 13;
				break;
			case "$t6":
				numeroRegistrador = 14;
				break;
			case "$t7":
				numeroRegistrador = 15;
				break;
			case "$s0":
				numeroRegistrador = 16;
				break;
			case "$s1":
				numeroRegistrador = 17;
				break;
			case "$s2":
				numeroRegistrador = 18;
				break;
			case "$s3":
				numeroRegistrador = 19;
				break;
			case "$s4":
				numeroRegistrador = 20;
				break;
			case "$s5":
				numeroRegistrador = 21;
				break;
			case "$s6":
				numeroRegistrador = 22;
				break;
			case "$s7":
				numeroRegistrador = 23;
				break;
			case "$t8":
				numeroRegistrador = 24;
				break;
			case "$t9":
				numeroRegistrador = 25;
				break;
			case "$gp":
				numeroRegistrador = 28;
				break;
			case "$sp":
				numeroRegistrador = 29;
				break;
			case "$fp":
				numeroRegistrador = 30;
				break;
			case "$ra":
				numeroRegistrador = 31;
				break;
		}
		
		return numeroRegistrador;
	}
	
}
