package edu.tyut.test;

import edu.tyut.adt.stack.MyStack;

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
		 MyStack<Integer> queue = new MyStack<>(4);
		 queue.push(1);
		 queue.push(2);
		 queue.pop();
		 queue.pop();
		 queue.pop();
		 queue.push(3);
		 queue.push(4);
		 queue.pop();
		 queue.push(5);
		 
		 System.out.println(queue.toString());
		 queue.push(10);
		 queue.push(20);
		  
		 System.out.println(queue.toString());
		 //queue.clear();
		 System.out.println(queue.toString());
		 Object[] ins = queue.toArray();
		 for (int i = 0; i < ins.length; i++) {
			 System.out.print(ins[i] + "--");
		 }
		/* Customer c1 = new Customer(0012, "XXX998");
		 Customer c2 = new Customer(0013, "XXX999");
		 Customer c3 = new Customer(0014, "XXX999");
		 Customer c4 = new Customer(0015, "XXX999");
		 Customer c5 = new Customer(0016, "XXX999");
		 Customer c6 = new Customer(0017, "XXX996");
		 queue.push(c1);
		 queue.push(c2);
		 queue.push(c3);
		 queue.push(c4);
		 queue.pop();
		 queue.push(c4);
		 queue.pop();
		 queue.push(c5);
		 queue.push(c6);
		 queue.clear();
		 System.out.println(queue.toString());*/
		 
	}
}
