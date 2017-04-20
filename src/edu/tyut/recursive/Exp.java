package edu.tyut.recursive;

import java.util.Scanner;

public class Exp {
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			scan.useDelimiter("\r\n");
			int input = scan.nextInt();
			Exp.print(input);
		}
	}
	
	public static void print(int n) {
		boolean first = true;
		for (int i = 15; i >= 0; i--) {
			if (Exp.getBit(n, i) == 1) {
				if (!first) {
					System.out.print("+");
				} else {
					first = false;
				}
					
				if ( i == 0) {
					System.out.print("2(0)");
				} else if (i == 1) {
					System.out.print("2");
				} else {
					System.out.print("2(");
					Exp.print(i);
					System.out.print(")");
				}
			}
		}
	}
	
	public static int getBit(int n, int i) {
		return (n >> i) & 1;
	}
}
