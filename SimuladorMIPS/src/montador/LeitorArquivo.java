package montador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import modelo.Instrucao;
import modelo.Variavel;

public class LeitorArquivo {
	private Scanner entrada;
	
	public void abrirArquivo() {
		try {
			entrada = new Scanner(new File("teste.txt"));
		} catch (FileNotFoundException e) {
			System.err.println( "Erro ao abrir o arquivo." );
	        System.exit( 1 );
	        System.out.println(entrada.next());
		}
	}
	
	public void lerDados(List<Variavel> listaVariaveis, List<Instrucao> listaInstrucoes) {
		//listaVariaveis = new ArrayList<Variavel>();
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
		if (entrada.next().equals(".globl") && entrada.next().equals("main") && entrada.next().equals("main:")) {
			System.out.println(entrada.next());
			System.out.println(entrada.next());
		}
	}
	
	public void fecharArquivo() {
		if (entrada != null) {
			entrada.close();
		}
	}
	public static void main(String[] args) {
		LeitorArquivo l = new LeitorArquivo();
		List<Variavel> lista = new ArrayList<Variavel>();
		List<Instrucao> lista2 = new ArrayList<Instrucao>();
		l.abrirArquivo();
		l.lerDados(lista, lista2);
		l.fecharArquivo();
		for (Variavel b : lista) {
			System.out.println(b.getNome());
			for (int c : b.getValores()) {
				System.out.println(c);
			}
		}
	}
}
