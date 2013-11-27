package util;

import java.util.StringTokenizer;

public class Teste {
	public static void main(String[] args) {
		StringTokenizer str = new StringTokenizer("sw a,a");
		//System.out.println(str.nextToken());
		String b = str.nextToken();
		if (b.equals("sw")) {
			System.out.println("vassas: " + b);
		}
		//System.out.println(str.nextToken(":").trim());
		//System.out.println(str.nextToken(",").trim());
		String a = "loop:";
		
		if (a.endsWith(":")) {
		System.out.println("va");
		}
	}
}
