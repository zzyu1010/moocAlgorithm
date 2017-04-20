package edu.tyut.recursive;

import java.util.Scanner;

public class IntegerPartition {
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			scan.useDelimiter("\r\n");
			int N = scan.nextInt();
			System.out.println(ways(N,N));
		}
	}
	public static int ways(int n, int i) {
		if (n == 0) {
			return 1;
		}
		if (i == 0) {
			return 0;
		}
		if (i <= n) {
			return ways(n-i, i) + ways(n, i-1);
		} else {
			return ways(n, i-1);
		}
	}
}
