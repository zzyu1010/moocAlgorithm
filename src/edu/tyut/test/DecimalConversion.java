package edu.tyut.test;

import edu.tyut.adt.stack.MyStack;

/**
 * @author ZZY, E-mail: zzyu1010@163.com
 * @version 0.0.1, created: 2017年4月26日下午5:00:24
 */
public class DecimalConversion {
	public static final int BINARY = 2;
	public static final int OCTONARY = 8; 
	public static final int HEXADECIMAL = 16;
	private static final char[] NUM = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	private MyStack<Integer> stack = new MyStack<>();
	
	public DecimalConversion() {}
	
	public String converse(int num, int base) {
		int mod = 0;
		StringBuilder sb = new StringBuilder();
		stack.clear();
		while (num != 0) {
			mod = num % base;
			stack.push(mod);
			num /= base;
		}
		if (base == HEXADECIMAL) {
			int tmp = 0;
			while (!stack.isEmpity()) {
				tmp = stack.pop();
				sb.append(NUM[tmp]);
			}
			return sb.toString();
		}
		return stack.toString().replaceAll(" ", "");
	}
}
