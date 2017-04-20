package edu.tyut.recursive;

import java.util.Scanner;

public class BooleanExpressions {
	private static char[] wholeExp = new char[200];
	private static int ptr = 0;

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			scan.useDelimiter("\r\n");
			int t = 0;
			String str = scan.next();
			str = str.replaceAll("\\s+", "");
			System.arraycopy(str.toCharArray(), 0, wholeExp, 0, str.length());
			boolean result = exp();
			if (result) {
				System.out.println("Expression " + (++t) + ": V");
			} else {
				System.out.println("Expression " + (++t) + ": F");
			}
		}
	}

	public static boolean exp() {
		boolean result = item();
		while (wholeExp[ptr] == '|') {
			++ptr;
			result |= item();
		}
		return result;
	}

	public static boolean item() {
		boolean result = factor();
		while (wholeExp[ptr] == '&') {
			++ptr;
			result &= factor();
		}
		return result;
	}

	public static boolean factor() {
		boolean result = false;
		switch (wholeExp[ptr]) {
		case 'V':
			++ptr;
			result = true;
			break;
		case 'F':
			++ptr;
			result = false;
			break;
		case '(':
			++ptr;
			result = exp();
			++ptr;
			break;
		case '!':
			result = notExp();
			break;
		}
		return result;
	}

	public static boolean notExp() {
		++ptr;
		boolean result = false;
		switch (wholeExp[ptr]) {
		case 'V':
			++ptr;
			result = false;
			break;
		case 'F':
			++ptr;
			result = true;
			break;
		case '(':
			++ptr;
			result = !exp();
			++ptr;
			break;
		case '!':
			result = !notExp();
			break;
		}
		return result;
	}
}
