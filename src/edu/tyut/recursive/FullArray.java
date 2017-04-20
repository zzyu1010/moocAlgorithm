package edu.tyut.recursive;

import java.util.Scanner;

public class FullArray {
	private static final int M = 8;
	private static int L;
	private static boolean[] used = new boolean[M];
	private static char[] permutation = new char[M];
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			scan.useDelimiter("\r\n");
			String oriStr = scan.next();
			FullArray.L = oriStr.length();
			char[] oriChars = oriStr.toCharArray();
			permutation(oriChars, 0);
		}
		
	}
	public static void permutation(char[] cs, int n) {
		
		if (n == L) {
			System.out.println(String.valueOf(permutation));
			return ;
		}
		
		for (int i = 0; i < L; i++) {
			 if (!used[i]) {
				 used[i] = true;
				 permutation[n] = cs[i];
				 permutation(cs, n+1);
				 used[i] = false;
			 }
		}
	}
}
