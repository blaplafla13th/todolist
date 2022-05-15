package blaplafla.todolist.models.test;


import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.datastructures.SimpleStack;

public class SyncTest {
    public static void main(String[] args) {
        System.out.println("ArrayList Sync Test");
        SimpleArrayList<String> arrayList = new SimpleArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        ArrayListWorker arrayListWorker1 = new ArrayListWorker(arrayList);
        ArrayListWorker arrayListWorker2 = new ArrayListWorker(arrayList);
        ArrayListWorker arrayListWorker3 = new ArrayListWorker(arrayList);
        arrayListWorker1.start();
        arrayListWorker2.start();
        arrayListWorker3.start();
        try {
            arrayListWorker1.join();
            arrayListWorker2.join();
            arrayListWorker3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Stack Sync Test");
        SimpleStack<String> stack = new SimpleStack<>();
        stack.add("a");
        stack.add("b");
        stack.add("c");
        StackWorker stackWorker1 = new StackWorker(stack);
        StackWorker stackWorker2 = new StackWorker(stack);
        StackWorker stackWorker3 = new StackWorker(stack);
        stackWorker1.start();
        stackWorker2.start();
        stackWorker3.start();
        try {
            stackWorker1.join();
            stackWorker2.join();
            stackWorker3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class ArrayListWorker extends Thread {
    SimpleArrayList<String> arrayList;

    public ArrayListWorker(SimpleArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void run() {
        System.out.println(arrayList.get(0));
        arrayList.removeIndex(0);
    }
}

class StackWorker extends Thread{
    SimpleStack<String> stack;

    public StackWorker(SimpleStack<String> stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        System.out.println(stack.pop());
    }
}
