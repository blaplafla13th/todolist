package blaplafla.todolist.models.test;

import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.datastructures.SimpleStack;

import java.util.concurrent.CountDownLatch;

public class SyncTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {

        SimpleArrayList<String> arrayList = new SimpleArrayList<>();
        SimpleArrayListWorker arrayListWorker1 = new SimpleArrayListWorker(arrayList);
        SimpleArrayListWorker arrayListWorker2 = new SimpleArrayListWorker(arrayList);

        SimpleStack<String> stack = new SimpleStack<>();
        SimpleStackWorker stackWorker1 = new SimpleStackWorker(stack);
        SimpleStackWorker stackWorker2 = new SimpleStackWorker(stack);

        arrayListWorker1.start();
        arrayListWorker2.start();
        stackWorker1.start();
        stackWorker2.start();

        try {
            arrayListWorker1.join();
            arrayListWorker2.join();
            stackWorker1.join();
            stackWorker2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(arrayList.size() + "-" + stack.size());
    }
}

class SimpleArrayListWorker extends Thread {
    SimpleArrayList<String> arrayList;

    public SimpleArrayListWorker(SimpleArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            arrayList.add("a");
        }
    }
}

class SimpleStackWorker extends Thread {
    SimpleStack<String> stack;

    public SimpleStackWorker(SimpleStack<String> SimpleStack) {
        this.stack = SimpleStack;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            stack.add("a");
        }
    }
}
