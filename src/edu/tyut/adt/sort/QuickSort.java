package edu.tyut.adt.sort;
/**
 * @title QuickSort.java
 * @description 快速排序：是交换排序的一种，但和冒泡排序不同的是，冒泡排序只比较相邻两个记录的顺序，
 * 而快速排序的原理是将未排序元素根据基准分为两个子序列，其中一个子序列的记录均大于基准，而另一个子序列
 * 均小于基准，然后递归地对这两个子序列用类似的方法进行排序。
 * @time 2017年5月23日上午9:05:02
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class QuickSort {
	/**
	 * 快速排序
	 * @param src
	 */
	public void quickSort(int[] src) {
		qsort(src, 0, src.length-1);
	}
	
	/**
	 * 对src[low]~src[high]进行快速排序，一般选取src[low]、
	 * src[high]、src[(low+high)/2]三者的关键字的中值作为基准
	 * @param src
	 * @param low 指向待排序序列的第一个元素
	 * @param high 指向待排序序列的倒数第二个元素
	 */
	private void qsort(int[] src, int low, int high) {
		int left = low, right = high;
		
		if (low >= high) { // 递归终止条件
			return ;
		}
		int pivot = src[low]; // 选择首元素作为基准
		swap(src,low,high); // 将基准元素与数组最后一个元素进行交换
		while (true) { // 将序列中比基准小的移动到基准左边，大的移动到右边
			for (; low < high && src[low] <= pivot; low++);
			for (; low < high && src[high] >= pivot; high--);
			if (low < high) {
				swap(src,low,high);
			} else {
				break;
			}
		}
		swap(src, low, right);
		// 分别对两个子集进行快排
		qsort(src,left,low-1);
		qsort(src,low+1,right);
	}
	
	private void swap(int[] src, int i, int j) {
		int temp = src[i];
		src[i] = src[j];
		src[j] = temp;
	}
	
}
