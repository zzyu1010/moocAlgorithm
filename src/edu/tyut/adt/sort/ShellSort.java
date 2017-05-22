package edu.tyut.adt.sort;
/**
 * @title ShellSort.java
 * @description 希尔排序：简单插入排序效率不高的一个重要原因是每次只交换相邻的两个元素，这样只能
 * 消去一对错位的元素。希尔排序对插入进行改进，试图通过每次交换相隔一定距离的元素，达到效率的提升。
 * 
 * 其基本原理是，将排序的一组元素按一定间隔分为若干个序列，分别进行插入排序。开始时设置的“间隔”较大，
 * 在每轮排序中将间隔逐步减小，直到“间隔”为1，也就是最后一步是进行简单排序。
 * 
 * 希尔排序将“间隔”定义为一组增量序列，用来分割带排序列，即将位置之差等于当前增量的元素归属于同一个子序列，
 * 并分别进行排序；排好后再选取下一个增量，划分子序列再次排序，直到最后一个增量（一般为1）。
 * @time 2017年5月22日下午8:32:31
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class ShellSort {
	/**
	 * 升序排列
	 * @param src 需要排序的数列
	 * @param incre 选择的增量序列，递减有序，且最后一个元素为1
	 * @param M  
	 */
	public void sort(int[] src, int[] incre) {
		int increment, temp, k ;
		for (int i = 0; i < incre.length; i++) { // 由incre.lenght个增量序列
			increment = incre[i];
			for (int j = increment; j < src.length; j++) { // 从增量开始，进行插入排序
				temp = src[j];
				for (k = j; k-increment >= 0; k -= increment) {
					 if ( temp >= src[k-increment]) {
						 break;
					 } else {
						 src[k] = src[k-increment];
					 }
				}
				src[k] = temp;
			}
		}
	}
}
