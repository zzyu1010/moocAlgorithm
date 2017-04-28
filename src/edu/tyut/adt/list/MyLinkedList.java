package edu.tyut.adt.list;

import java.util.NoSuchElementException;

/**
 * @title MyLinkedList.java
 * @description 单链表实现
 * @time 2017年4月28日上午9:49:15
 * @author
 * 		<li>ZZY</li>
 *         <li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1
 */
public class MyLinkedList<E> {
	private static class Node<E> {
		private E item;
		private Node<E> next;

		public Node(E element, Node<E> next) {
			this.item = element;
			this.next = next;
		}

		public Node(E element) {
			this(element, null);
		}

		public Node() {
		}

		public boolean equals(Node<E> x) {
			if (x == null) {
				return false;
			}
			if (this.item == null && x.item == null) {
				return true;
			}
			if (this.item != null && this.item.equals(x.item)) {
				return true;
			}
			return false;
		}
	}

	private Node<E> head = new Node<E>();
	private int size;

	private void checkElementIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private Node<E> node(int index) {
		Node<E> result = head;
		for (int i = 0; i <= index; i++) {
			result = result.next;
		}
		return result;
	}

	public E get(int index) {
		checkElementIndex(index);
		return node(index).item;
	}

	public E set(int index, E e) {
		checkElementIndex(index);
		Node<E> inode = node(index);
		E oldValue = inode.item;
		inode.item = e;
		return oldValue;
	}

	public E getFirst() {
		if (head.next == null) {
			throw new NoSuchElementException();
		}
		return head.next.item;
	}

	public E getLast() {
		Node<E> last = node(size - 1);
		if (last == head) {
			throw new NoSuchElementException();
		}
		return last.item;
	}

	public Node<E> before(Node<E> node) {
		if (size == 0) {
			return null;
		}
		Node<E> x = head;
		Node<E> curNode = head;
		while (x.next != null) {
			curNode = x.next;
			if (curNode.equals(node)) {
				return x;
			}
			x = curNode;
		}
		return null;
	}

	public E removeFirst() {
		if (head.next == null) {
			throw new NoSuchElementException();
		}
		E oldValue = head.next.item;
		Node<E> first = head.next;
		head.next = first.next;
		first.item = null;
		first.next = null;
		size--;
		return oldValue;
	}

	public E removeLast() {
		Node<E> preLast = new Node<>();
		Node<E> last = new Node<>();
		if (size <= 0) {
			throw new NoSuchElementException();
		}
		if (size == 1) {
			preLast = head;
			last = head.next;
		}
		if (size >= 2)  {
			preLast = node(size-2);
			last = node(size-1);
		}
		E oldValue = last.item;
		preLast.next = null;
		last.item = null;
		last.next = null;
		size--;
		return oldValue;
	}

	public void addFirst(E e) {
		Node<E> newNode = new Node<>(e);
		if (head.next == null) {
			head.next = newNode;
		} else {
			newNode.next = head.next;
			head.next = newNode;
		}
		size++;
	}

	public void addLast(E e) {
		Node<E> newNode = new Node<>(e);
		Node<E> last = head;
		while (last.next != null) {
			last = last.next;
		}
		last.next = newNode;
		size++;
	}

	private String outOfBoundsMsg(int index) {
		return "index: " + index + ", size: " + size;
	}

	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
		Node<E> newNode = new Node<>(e);
		Node<E> preNode = head;
		for (int i = 0; i < index; i++) {
			preNode = preNode.next;
		}
		newNode.next = preNode.next;
		preNode.next = newNode;
		size++;

	}
	public E remove(int index) {
		checkElementIndex(index);
		Node<E> x = node(index-1);
		Node<E> currentNode = x.next;
		x.next = currentNode.next;
		E result = currentNode.item;
		currentNode.item = null;
		currentNode.next = null;
		size--;
		return result;
	}
	public void clear() {
		Node<E> x = head;
		Node<E> temp = head;
		while (x.next != null) {
			temp = x.next;
			x.next = null;
			x.item = null;
			x = temp;
		}
		size = 0;
	}

	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (Node<E> x = head.next; x != null; x = x.next) {
			result[i++] = x.item;
		}
		return result;
	}

	public boolean contains(Object o) {
		return indexOf(o) == -1 ? false : true;
	}

	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	public int indexOf(Object o) {
		int index = 0;
		if (o == null) {
			for (Node<E> x = head.next; x != null; x = x.next) {
				if (x.item == null) {
					return index;
				}
				index++;
			}
		} else {
			for (Node<E> x = head.next; x != null; x = x.next) {
				if (o.equals(x.item)) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}

	public int size() {
		return size;
	}

}
