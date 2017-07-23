package edu.tyut.test;

import edu.tyut.adt.map.AdjacencyMatrixMooc;
import edu.tyut.adt.map.AdjacencyMatrixMooc.Node;

public class Test {
 
	 
	public static void main(String...args) {
		AdjacencyMatrixMooc am = new AdjacencyMatrixMooc(8);
		//AdjacencyMatrixMooc.Node node1 = new AdjacencyMatrixMooc(8).new Node('A');
		Node node1 = am.new Node('A');
		Node node2 = am.new Node('B');
		Node node3 = am.new Node('C');
		Node node4 = am.new Node('D');
		Node node5 = am.new Node('E');
		Node node6 = am.new Node('F');
		Node node7 = am.new Node('G');
		Node node8 = am.new Node('H');
		
		am.addNode(node1);
		am.addNode(node2);
		am.addNode(node3);
		am.addNode(node4);
		am.addNode(node5);
		am.addNode(node6);
		am.addNode(node7);
		am.addNode(node8);
		
		am.setValueToMatrixForUndirectedGraph(0, 1);
		am.setValueToMatrixForUndirectedGraph(0, 3);
		am.setValueToMatrixForUndirectedGraph(1, 2);
		am.setValueToMatrixForUndirectedGraph(1, 5);
		am.setValueToMatrixForUndirectedGraph(2, 4);
		am.setValueToMatrixForUndirectedGraph(3, 6);
		am.setValueToMatrixForUndirectedGraph(3, 7);
		am.setValueToMatrixForUndirectedGraph(4, 5);
		am.setValueToMatrixForUndirectedGraph(6, 7);
	 
		am.printMatrix();
		
		am.depthFirstTraverse(0);
		am.resetNode();
		System.out.println();
		am.breadthFirstTraverse(0);
	}
		 
}

 

 
 
	
 
