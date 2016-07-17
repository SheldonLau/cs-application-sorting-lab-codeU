/**
 * 
 */
package com.flatironschool.javacs;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
    
    if(list.size() <= 1) {
      return list;
    }
    List<T> left = new ArrayList<T>();
    List<T> right = new ArrayList<T>();

    for(int i = 0; i < list.size(); i++) {
      if(i % 2 == 0) {
        left.add(list.get(i));
      }
      else {
        right.add(list.get(i));
      }
    }
    left = mergeSort(left, comparator);
    right = mergeSort(right, comparator);
    
    List<T> sorted = merge(left, right, comparator);
    return sorted;
	}

  public List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
    List<T> sorted = new ArrayList<T>();
    while(left.size() != 0 && right.size() != 0) {
      if(comparator.compare(left.get(0), right.get(0)) < 0) {
        sorted.add(left.get(0));
        left.remove(0);
      }
      else {
        sorted.add(right.get(0));
        right.remove(0);
      }
    }

    while(left.size() != 0) {
      sorted.add(left.get(0));
      left.remove(0);
    }

    while(right.size() != 0) {
      sorted.add(right.get(0));
      right.remove(0);
    }

    return sorted;
  } 

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
    PriorityQueue<T> prq = new PriorityQueue<T>();
    for(int i = 0; i < list.size(); i++) {
      prq.offer(list.get(i));
    }
    list.clear();
    while(!prq.isEmpty()) {
        list.add(prq.poll());
    }
    
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
	  PriorityQueue<T> prq = new PriorityQueue<T>();
    List<T> sorted = new ArrayList<T>();
    for(T element : list) {
      if(prq.size() < k) {
        prq.offer(element);
      }
      else {
        T tempSmallest = prq.peek();
        int smaller = comparator.compare(element, tempSmallest);
        if(smaller > 0) {
          prq.remove(tempSmallest);
          prq.offer(element);
        }
      }
    }
    while(!prq.isEmpty()) {
        sorted.add(prq.poll());
    }
    return sorted;
  }

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
