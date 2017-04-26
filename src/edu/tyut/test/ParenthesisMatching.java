package edu.tyut.test;

import edu.tyut.adt.stack.MyStack;

/**
 * @author ZZY, E-mail: zzyu1010@163.com
 * @version 0.0.1, created: 2017年4月26日下午7:26:03
 */
public class ParenthesisMatching {
	private MyStack<Character> stack = new MyStack<>();
	private MyStack<Character> matchStack = new MyStack<>();
	public ParenthesisMatching() {}
	
	public boolean match(String str) {
		char[] signs = str.replaceAll(" ", "").toCharArray();
		char needSign = ' ';
		stack.clear();
		matchStack.clear();
		for (int i = 0; i < signs.length; i++) {
			if (signs[i] != needSign) {
				stack.push(signs[i]);
				switch (signs[i]) {
					case '[' :
						if (needSign != ' ') {
							matchStack.push(needSign);
						}
						needSign = ']';
						break;
					case '(' :
						if (needSign != ' ') {
							matchStack.push(needSign);
						}
						needSign = ')';
						break;
					default:
						return false;
				}
			} else {
				stack.pop();
				needSign = matchStack.isEmpity() ? ' ' : matchStack.pop();
			}
		}
		return stack.isEmpity() ? true : false;
	}
}
