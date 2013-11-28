package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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

public class Execucao {
	
	private Montador m = new Montador();
	private List<Variavel> lista = new ArrayList<Variavel>();
	
	private MemoriaInstrucoes memoriaInstrucoes = new MemoriaInstrucoes();
	private BancoRegistradores bancoRegistradores = new BancoRegistradores();
	private HashMap< Integer, SinalControle > sinaisControle = new HashMap< Integer, SinalControle >();
	private Instrucao instrucaoAtual = new Instrucao();
	
	public void executar(String nomeArquivo) {
		
		init(nomeArquivo);
		
		this.instrucaoAtual = this.memoriaInstrucoes.getInstrucao( 0 );
		
		do {
			System.out.println( "Buscando instrução..." );
			this.sinaisControle.get( 4 ).modificarValor( "" );
			this.sinaisControle.get( 7 ).modificarValor( "" );
			this.sinaisControle.get( 2 ).modificarValor( "" );
			this.sinaisControle.get( 11 ).modificarValor( "0" );
			this.sinaisControle.get( 3 ).modificarValor( "0" );
			this.sinaisControle.get( 10 ).modificarValor( "01" );
			this.sinaisControle.get( 9 ).modificarValor( "00" );
			this.sinaisControle.get( 8 ).modificarValor( "00" );
			
			this.imprimirSinaisDeControleAtivos();
			this.inativarSinaisControle();
			this.realizarInstrucao( this.instrucaoAtual );
			this.instrucaoAtual = this.memoriaInstrucoes.getInstrucao(this.instrucaoAtual.getEnderecoProximaInstrucao());
		}while ( this.instrucaoAtual != null );
	}
	
	private void realizarInstrucao( Instrucao instrucao ) {
		
		System.out.println( "Decodificando instrução e buscando registrador..." );
		this.sinaisControle.get( 11 ).modificarValor( "0" );
		this.sinaisControle.get( 10 ).modificarValor( "11" );
		this.sinaisControle.get( 9 ).modificarValor( "00" );
		
		this.imprimirSinaisDeControleAtivos();
		this.inativarSinaisControle();
		
		if( instrucao instanceof Add || instrucao instanceof Sub || instrucao instanceof And || instrucao instanceof Or )
			instrucaoTipoR( instrucao );
		else if( instrucao instanceof Lw || instrucao instanceof Sw ) 
			instrucaoTipoLoadStore( instrucao );
		else if( instrucao instanceof Beq ) 
			instrucaoTipoBeq( instrucao );
		else if( instrucao instanceof J ) 
			instrucaoTipoJ( instrucao );
	}
		
	private void instrucaoTipoR( Instrucao instrucao ){
		
		System.out.println( "Execução da instrucao tipo R..." );
		
		this.sinaisControle.get( 11 ).modificarValor( "1" );
		this.sinaisControle.get( 10 ).modificarValor( "00" );
		this.sinaisControle.get( 9 ).modificarValor( "10" );
		
		this.imprimirSinaisDeControleAtivos();
		this.inativarSinaisControle();
		
		Registrador regRd = new Registrador();
		Registrador regRs = new Registrador();
		Registrador regRt = new Registrador();
		int resultado;
		
		
		if( instrucao instanceof Add ) {
			regRd = this.bancoRegistradores.getRegistrador( ( (Add) instrucao ).getRd().getId() );
			regRs = this.bancoRegistradores.getRegistrador( ( (Add) instrucao ).getRs().getId() );
			regRt = this.bancoRegistradores.getRegistrador( ( (Add) instrucao ).getRt().getId() );
			
			resultado = regRs.getValor() + regRt.getValor();
			regRd.setValor( resultado );
			
			this.bancoRegistradores.getRegistrador( regRd.getId() ).setValor( regRd.getValor() );
		}else if( instrucao instanceof Sub ) {
			regRd = this.bancoRegistradores.getRegistrador( ( (Sub) instrucao ).getRd().getId() );
			regRs = this.bancoRegistradores.getRegistrador( ( (Sub) instrucao ).getRs().getId() );
			regRt = this.bancoRegistradores.getRegistrador( ( (Sub) instrucao ).getRt().getId() );
			
			resultado = regRs.getValor() - regRt.getValor();
			regRd.setValor( resultado );
			
			this.bancoRegistradores.getRegistrador( regRd.getId() ).setValor( regRd.getValor() ); 
		}else if( instrucao instanceof And ) {
			regRd = this.bancoRegistradores.getRegistrador( ( (And) instrucao ).getRd().getId() );
			regRs = this.bancoRegistradores.getRegistrador( ( (And) instrucao ).getRs().getId() );
			regRt = this.bancoRegistradores.getRegistrador( ( (And) instrucao ).getRt().getId() );
			
			resultado = regRs.getValor() & regRt.getValor();
			regRd.setValor( resultado );
			
			this.bancoRegistradores.getRegistrador( regRd.getId() ).setValor( regRd.getValor() ); 
		}else if( instrucao instanceof Or ) {
			regRd = this.bancoRegistradores.getRegistrador( ( (Or) instrucao ).getRd().getId() );
			regRs = this.bancoRegistradores.getRegistrador( ( (Or) instrucao ).getRs().getId() );
			regRt = this.bancoRegistradores.getRegistrador( ( (Or) instrucao ).getRt().getId() );
			
			resultado = regRs.getValor() | regRt.getValor();
			regRd.setValor( resultado );
			
			this.bancoRegistradores.getRegistrador( regRd.getId() ).setValor( regRd.getValor() ); 
		}
		
		System.out.println("Termino da instrução...");
		this.sinaisControle.get( 13 ).modificarValor( "1" );
		this.sinaisControle.get( 12 ).modificarValor( "" );
		this.sinaisControle.get( 6 ).modificarValor( "0" );
		
		this.imprimirSinaisDeControleAtivos();
		this.inativarSinaisControle();
		
		System.out.println("Novo valor do registrador " + regRd.getNome() + ": " + regRd.getValor() + "\n");
		
	}
	
	private void instrucaoTipoLoadStore( Instrucao instrucao ) {
		
		System.out.println( "Calculo do endereco de memoria..." );
		this.sinaisControle.get( 11 ).modificarValor( "1" );
		this.sinaisControle.get( 10 ).modificarValor( "10" );
		this.sinaisControle.get( 9 ).modificarValor( "00" );
		
		this.imprimirSinaisDeControleAtivos();
		this.inativarSinaisControle();
		
		System.out.println( "Acesso a memoria..." );
		Registrador reg = new Registrador();
		Variavel variavel = new Variavel();
		if( instrucao instanceof Sw ) {
			this.sinaisControle.get( 5 ).modificarValor( "" );
			this.sinaisControle.get( 3 ).modificarValor( "1" );
			
			this.imprimirSinaisDeControleAtivos();
			this.inativarSinaisControle();
			
			String nomeVariavel = ( (Sw) instrucao ).getNomeVariavel();
			for( Variavel v : lista ) {
				if( v.getNome().equals( nomeVariavel ) ) {
					reg = this.bancoRegistradores.getRegistrador( ( (Sw) instrucao ).getRt().getId() );
					v.setValor( reg.getValor(), 
							this.bancoRegistradores.getRegistrador( ( (Sw) instrucao ).getRs().getId() ).getValor()) ;
					variavel = v;
				}
			}
			
			System.out.println("Novo valor da variavel " + variavel.getNome() + ": " + reg.getValor( ) + "\n" );
		}else{
			this.sinaisControle.get( 4 ).modificarValor( "" );
			this.sinaisControle.get( 3 ).modificarValor( "1" );
			
			this.imprimirSinaisDeControleAtivos();
			this.inativarSinaisControle();
			
			String nomeVariavel = ( (Lw) instrucao ).getNomeVariavel();
			for( Variavel v : lista ) {
				if( v.getNome().equals( nomeVariavel ) ) {
					reg = this.bancoRegistradores.getRegistrador( ( (Lw) instrucao ).getRt().getId() );
					variavel = v;
				}
			}
			
			//Escrita
			System.out.println( "Passo de escrita..." );
			this.sinaisControle.get( 13 ).modificarValor( "0" );
			this.sinaisControle.get( 12 ).modificarValor( "" );
			this.sinaisControle.get( 6 ).modificarValor( "1" );
			
			this.imprimirSinaisDeControleAtivos();
			this.inativarSinaisControle();
			
			Registrador regRs = this.bancoRegistradores.getRegistrador(((Lw) instrucao).getRs().getId());
			int endereco = regRs.getValor();
			
			reg.setValor( variavel.getValor(endereco));
			
			System.out.println("Valor do registrador " + reg.getNome() + ": " + reg.getValor() + "\n" );
			
			this.bancoRegistradores.getRegistrador( reg.getId() ).setValor( reg.getValor() );
		}
		
	}
	
	private void instrucaoTipoBeq( Instrucao instrucao ) {
		Registrador regRs = this.bancoRegistradores.getRegistrador(((Beq)instrucao).getRs().getId());
		Registrador regRt = this.bancoRegistradores.getRegistrador(((Beq)instrucao).getRt().getId());
		
		
		if (regRs.getValor() == regRt.getValor()) {
			this.instrucaoAtual.setEnderecoProximaInstrucao(((Beq)instrucao).getEndereco());
			System.out.println("Condicional verdadeiro!\n");
		} else {
			System.out.println("Condicional falso!\n");
		}
		
		System.out.println( "Termino do desvio condicional..." );
		this.sinaisControle.get( 11 ).modificarValor( "1" );
		this.sinaisControle.get( 10 ).modificarValor( "00" );
		this.sinaisControle.get( 9 ).modificarValor( "01" );
		this.sinaisControle.get( 1 ).modificarValor( "" );
		this.sinaisControle.get( 8 ).modificarValor( "01" );
		
		
		
		this.imprimirSinaisDeControleAtivos();
		this.inativarSinaisControle();
		
		
	}
	
	private void instrucaoTipoJ( Instrucao instrucao ) {
		
		System.out.println( "Termino do desvio incondicional..." );
		this.sinaisControle.get( 2 ).modificarValor( "" );
		this.sinaisControle.get( 8 ).modificarValor( "10" );
		
		this.imprimirSinaisDeControleAtivos();
		this.inativarSinaisControle();
		
		this.instrucaoAtual.setEnderecoProximaInstrucao(((J) instrucao).getEndereco());
		
	}
	
	private void init(String nomeArquivo) {
		
		this.m.montar(nomeArquivo, this.lista, this.memoriaInstrucoes, this.bancoRegistradores);
		
		this.sinaisControle.put( 1, new SinalControle( "PCEscCond" ) );
		this.sinaisControle.put( 2, new SinalControle( "PCEsc" ) );
		this.sinaisControle.put( 3, new SinalControle( "louD" ) );
		this.sinaisControle.put( 4, new SinalControle( "LerMem" ) );
		this.sinaisControle.put( 5, new SinalControle( "EscMem" ) );
		this.sinaisControle.put( 6, new SinalControle( "MemParaReg" ) );
		this.sinaisControle.put( 7, new SinalControle( "IREsc" ) );
		this.sinaisControle.put( 8, new SinalControle( "FontePC" ) );
		this.sinaisControle.put( 9, new SinalControle( "UALOp" ) );
		this.sinaisControle.put( 10, new SinalControle( "UALFonteB" ) );
		this.sinaisControle.put( 11, new SinalControle( "UALFonteA" ) );
		this.sinaisControle.put( 12, new SinalControle( "EscReg" ) );
		this.sinaisControle.put( 13, new SinalControle( "RegDst" ) );
		
	}
	
	private void inativarSinaisControle() {
		
		for( SinalControle sc : this.sinaisControle.values() )  {
			sc.setAtivo( false );
		}
		
	}
	
	private void imprimirSinaisDeControleAtivos() {
		
		System.out.println("Sinais de controle ativos:");
		for( SinalControle sc : this.sinaisControle.values() )  {
			if( sc.isAtivo() ) {
				System.out.println( sc.getNome() + ( !sc.getValor().equals( "" ) ? " = " + sc.getValor() + "" : "" ) );
			}
		}
		System.out.println("");
		
	}
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		System.out.print("Digite o nome do arquivo: ");
		String nomeArquivo = leitor.next();
		Execucao exec = new Execucao();
		exec.executar(nomeArquivo);
		leitor.close();
	}

}
