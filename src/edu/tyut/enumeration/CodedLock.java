package edu.tyut.enumeration;

import java.util.Scanner;

public class CodedLock {
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			scan.useDelimiter("\r\n");
			String oriStr = scan.next();
			String resultStr = scan.next();
			int oriLock = 0;
			int lock = 0;
			int destLock = 0;
			int minTimes = 1 << 30;
			int N = oriStr.length();
			for (int i = 0; i < N; i++) {
				oriLock = setBit(oriLock, i,  oriStr.charAt(i)- '0');
			}
			for (int j = 0; j < N; j++) {
				destLock = setBit(destLock, j, resultStr.charAt(j) - '0');
			}
			for (int m = 0; m < 2; m++) {
				lock = oriLock;
				int curButton = m;
				int times = 0;
				for (int n = 0; n < N; n++) {
					if (curButton == 1) {
						times ++;
						if (n > 0)  
							lock = flipBit(lock, n-1);
						lock = flipBit(lock,n);
						if ( n < N-1) 
							lock = flipBit(lock,n+1);
					}
					curButton = getBit(lock,n) == getBit(destLock, n) ? 0 : 1;
				}
				if (lock == destLock) {
					minTimes = Math.min(minTimes, times);
				}
				
			}
			if (minTimes == 1 << 30) {
				System.out.println("impossible");
			} else {
				System.out.println(minTimes);
			}
		}
	}
	public static int setBit(int n, int i, int v) {
		if (v == 1) {
			n |= 1 << i;
		} else {
			n &= ~(1 << i);
		}
		return n;
	}
	public static int getBit(int n, int i) {
		return (n >> i) & 1;
	}
	public static int flipBit(int n, int i) {
		return n ^ (1 << i);
	}
}
