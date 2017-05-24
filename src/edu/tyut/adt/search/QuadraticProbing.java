package edu.tyut.adt.search;
/**
 * @title QuadraticProbing.java
 * @description 散列查找，利用 平方探测方法解决冲突
 * 
 * 在开放定址散列表中，不能进行标准的删除操作，因为相应的单元可能已经发生过冲突，数据对象绕过它存在了别处。
 * 为此，开放定址散列表需要“懒惰删除”，即增加一个“删除标记”，而并不是真正的删除它。这样才能不影响查找，
 * 但增加了额外的存储负担并提高了程序的复杂程度。
 * @time 2017年5月24日上午11:08:45
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class QuadraticProbing {
	/**
	 * 数据删除标记
	 * LEGITIMATE: 存在合法数据；EMPTY: 空单元，可以插入数据； DELETED ：标记数据已删除，可以插入数据
	 * @author ZZY
	 *
	 */
	enum EntryType {LEGITIMATE, EMPTY, DELETED};
	/**
	 * 散列表中存放的数据最小单元
	 * @author ZZY
	 *
	 */
	private class HashEntry {
		int element;
		EntryType info;
	}
	/**
	 * 散列表
	 * @author ZZY
	 *
	 */
	private class HashTable {
		/**
		 * 有证明表示，如果散列表长度size是某个4k+3(k是正整数)形式的素数，平方探测法就可以
		 * 探查到整个散列表空间。这一点很重要，是我们能够放心使用平方探测法的理论保证。
		 */
		int size; //  散列表长度
		HashEntry[] cells;
	}
	
	private HashTable hashTable; // 全局散列表
	
	public QuadraticProbing(int tableSize) {
		initializeHashTable(tableSize);
	}
	
	public int getValue(int index) {
		if (index < 0 || index >= hashTable.size) {
			return -1;
		}
		return hashTable.cells[index].element;
	}
	
	/*public int getCollisionCount() {
		
	}*/
	
	/**
	 * 散列表的初始化函数
	 * @param tableSize
	 */
	private void initializeHashTable(int tableSize) {
		// 分配散列表
		hashTable = new HashTable();
		// 确定一个不小于tableSize的素数，用作真正的散列表的地址空间大小 
		hashTable.size = getNextPrime(tableSize); 
		// 分配散列表的地址列表数组
		hashTable.cells = new HashEntry[hashTable.size];
		// 初始化单元数组
		for (int i = 0; i < hashTable.size; i++) {
			HashEntry entry = new HashEntry();
			entry.info = EntryType.EMPTY;
			hashTable.cells[i] = entry;
		}
	}
	
	/**
	 * 找出给定数值相邻的素数，且满足4k+3形式
	 * @param number
	 * @return
	 */
	private int getNextPrime(int number) {
		int candidate = number;
		boolean flag = false; // 为false 表示找到素数
		while (true) {
			flag = false;
			candidate++;
			for (int i = 2; i <= Math.sqrt(candidate); i++) {
				if (candidate % i == 0) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				if ((candidate-3)%4 == 0) {
					break;
				}
			}
		}
		return candidate;
	}
	
	/**
	 * 查找函数,默认不包含相同的数据
	 * 
	 * 因为采用的tableSize大于关键字序列的个数，所以肯定有空元素，可以采用空元素判断作为
	 * 循环终止条件
	 * @param key 要查找的数据
	 * @return 若找到了关键字，函数直接返回结点的地址，若找不到返回可以插入key数据的空地址
	 */
	public int find(int key) {
		int currentPos,newPos,cNum;
		
		cNum = 0; // 记录冲突次数
		newPos = currentPos = hash(key, hashTable.size);
		// 直到查找成功或者找到空位置表示查找失败
		while (hashTable.cells[newPos].info != EntryType.EMPTY && hashTable.cells[newPos].element != key) {
			if (++cNum % 2 != 0) { // 判断冲突的奇偶次,奇次(1^2,2^2,3^2,...)
				newPos = currentPos + (cNum+1)/2 * (cNum+1)/2;
				while (newPos >= hashTable.size) {
					newPos -= hashTable.size;
				}
			} else { // 偶次(-1^2,-2^2,-3^2,...)
				newPos = currentPos - cNum/2 * cNum/2;
				while (newPos < 0) {
					newPos += hashTable.size;
				}
			}
		}
		 
		return newPos;
	}
	
	/**
	 * 插入数据，先计算散列地址，该地址的数据对象只要不是合法(Legitimate)的元素，就可以确定在此插入。
	 * 不可以插入相同的数据
	 * @param key
	 */
	public void insert(int key) {
		int pos = find(key);
		if (hashTable.cells[pos].info != EntryType.LEGITIMATE) { // 可以插入
			hashTable.cells[pos].info = EntryType.LEGITIMATE;
			hashTable.cells[pos].element = key;
		}
	}
	
	/**
	 * 删除数据，“懒惰删除”或“逻辑删除”
	 * @param key
	 */
	public void delete(int key) {
		int pos = find(key);
		if (hashTable.cells[pos].info == EntryType.LEGITIMATE) { // 只有是合法数据才能删除
			hashTable.cells[pos].info = EntryType.DELETED; // 标记删除
		}
	}
	
	public void clear() {
		for (HashEntry entry : hashTable.cells) {
			entry.info = EntryType.EMPTY;
		}
		
	}
	
	private int hash(int key, int tableSize) {
		return key % tableSize;
	}
	
	public void print() {
		int i = 0;
		for (HashEntry entry : hashTable.cells) {
			System.out.print("[" + (i++) +": " + entry.element + "] ");
		}
	}
}
