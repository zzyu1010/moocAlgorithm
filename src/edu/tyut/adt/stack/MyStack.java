package edu.tyut.adt.stack;

/**
 * @author ZZY, E-mail: zzyu1010@163.com
 * @version 0.0.1, created: 2017年4月20日下午5:20:28
 */
public class MyStack<E> {
	private int top = -1;
	private static final int DEFAULT_CAPACITY = 4;
	private int capacity;
	private Object[] elements;

	public MyStack() {
		elements = new Object[DEFAULT_CAPACITY];
		capacity = DEFAULT_CAPACITY;
	}

	public MyStack(int capacity) {
		this.capacity = capacity;
		elements = new Object[capacity];
	}

	public boolean push(E e) {
		elements[++top] = e;
		if (isFull()) {
			doubleCapacity();
		}
		return true;
	}

	private void doubleCapacity() {
		capacity <<= 1;
		Object[] tmp = new Object[capacity];
		System.arraycopy(elements, 0, tmp, 0, top + 1);
		elements = tmp;
	}

	public E pop() {
		if (isEmpity()) {
			return null;
		}
		@SuppressWarnings("unchecked")
		E e = (E) elements[top--];
		return e;
	}

	public void clear() {
		while (top >= 0) {
			elements[top--] = null;
		}
	}

	public boolean isEmpity() {
		return top == -1 ? true : false;
	}

	public boolean isFull() {
		return top == capacity - 1 ? true : false;
	}

	public Object[] toArray() {
		if (isFull()) {
			return reverse(elements);
		}
		Object[] tmp = new Object[0];
		if (isEmpity()) {
			return tmp;
		}
		tmp = new Object[top+1];
		System.arraycopy(elements, 0, tmp, 0, top + 1);
		return reverse(tmp);
	}
	private Object[] reverse(Object[] a) {
		int len = 0;
		Object[] tmp = new Object[a.length];
		for (int i = a.length-1; i >= 0; i--) {
			tmp[len++] = a[i];
		}
		return tmp;
	}
	public String toString() {
		if (isEmpity()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = top; i >= 0; i--) {
			sb.append(elements[i] + " ");
		}
		return sb.toString();
	}
}
