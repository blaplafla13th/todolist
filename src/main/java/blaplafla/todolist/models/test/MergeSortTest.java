package blaplafla.todolist.models.test;

import blaplafla.todolist.models.datastructures.SimpleArrayList;

// Java program for implementation of Heap Sort
public class MergeSortTest {
    public static void main(String[] args) {
        SimpleArrayList<Integer> test = new SimpleArrayList<>();
        for (int i = 0; i < 500; i++) {
            test.add((int) (10000 * Math.random()));
        }
        test.sort();
        for (Integer t : test) {
            System.out.print(t + " ");
        }
    }
}


