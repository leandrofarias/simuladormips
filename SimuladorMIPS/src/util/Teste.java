package util;

import java.util.StringTokenizer;

public class Teste {
	public static void main(String[] args) {
		StringTokenizer str = new StringTokenizer("lw $t0,a($t1)");
		System.out.println(str.nextToken());
		System.out.println(str.nextToken(",").trim());
		System.out.println("reg2" + str.nextToken(",").trim());
		//if (str.hasMoreTokens())
			
		StringTokenizer str2 = new StringTokenizer(str.nextToken());
		System.out.println(str2.nextToken("("));
		System.out.println(str2.nextToken(")").substring(1));
		
	}
}
