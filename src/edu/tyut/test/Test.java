package edu.tyut.test;

import edu.tyut.adt.queue.MyQueue;

class Customer {
	private int no;
	private String id;
	
	public Customer() {}
	public Customer(int no, String id) {
		this.no = no;
		this.id = id;
	}
	public String toString() {
		return "编号：" + no + "，证件：" + id;
	}
}

public class Test {
	public static void main(String[] args) {
		 MyQueue<Customer> queue = new MyQueue<>(4);
		/* queue.addLast(1);
		 queue.addLast(2);
		 queue.addLast(3);
		 queue.addLast(4);
		 queue.addLast(5);
		 System.out.println(queue.pollFirst());
		 System.out.println(queue.toString());
		 queue.addLast(10);
		 queue.addLast(20);
		 System.out.println(queue.pollFirst());
		 System.out.println(queue.toString());
		 queue.clear();
		 System.out.println(queue.toString());
		 Object[] ins = queue.toArray();
		 for (int i = 0; i < ins.length; i++) {
			 System.out.print(ins[i] + "--");
		 }*/
		 Customer c1 = new Customer(0012, "XXX999");
		 Customer c2 = new Customer(0013, "XXX999");
		 Customer c3 = new Customer(0014, "XXX999");
		 Customer c4 = new Customer(0015, "XXX999");
		 Customer c5 = new Customer(0016, "XXX999");
		 Customer c6 = new Customer(0017, "XXX999");
		 queue.addLast(c1);
		 queue.addLast(c2);
		 queue.addLast(c3);
		 queue.addLast(c4);
		 queue.addLast(c5);
		 queue.addLast(c6);
		 System.out.println(queue.toString());
		 
	}
}
