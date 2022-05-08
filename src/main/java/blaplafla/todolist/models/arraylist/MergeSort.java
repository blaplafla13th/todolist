package blaplafla.todolist.models.arraylist;

@SuppressWarnings("unchecked")

public class MergeSort<T extends Comparable<? super T>> {
    private T[] array;

    public MergeSort(T[] array) {
        this.array = array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    public void merge(T[] array1, T[] array2) {
        int i = 0;
        int j = 0;
        int r = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i].compareTo(array2[j]) <= 0) {
                array[r] = array1[i];
                i++;
                r++;
            } else {
                array[r] = array2[j];
                j++;
                r++;
            }
            if (i == array1.length) {
                while (j < array2.length) {
                    array[r] = array2[j];
                    r++;
                    j++;
                }
            }
            if (j == array2.length) {
                while (i < array1.length) {
                    array[r] = array1[i];
                    r++;
                    i++;
                }
            }
        }
    }

    public void sort(int n) {
        T[] subArray1 = (T[]) new Comparable[n / 2];
        T[] subArray2 = (T[]) new Comparable[n - n / 2];
        System.arraycopy(array, 0, subArray1, 0, n / 2);
        System.arraycopy(array, n / 2, subArray2, 0, n - n / 2);
        Worker runner1 = new Worker(subArray1);
        Worker runner2 = new Worker(subArray2);
        runner1.start();
        runner2.start();
        try {
            runner1.join();
            runner2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        merge(runner1.getArray(), runner2.getArray());
    }

    class Worker extends Thread {
        private T[] array;

        public Worker(T[] array) {
            this.array = array;
        }

        public T[] getArray() {
            return array;
        }

        @Override
        public void run() {
            mergeSort(array);
        }

        public void mergeSort(T[] array) {
            if (array.length > 1) {
                T[] left = (T[]) new Comparable[array.length / 2];
                T[] right = (T[]) new Comparable[array.length - array.length / 2];
                System.arraycopy(array, 0, left, 0, array.length / 2);
                System.arraycopy(array, array.length / 2, right, 0, array.length - array.length / 2);
                mergeSort(left);
                mergeSort(right);
                merge(array, left, right);
            }
        }

        public void merge(T[] result, T[] left, T[] right) {
            int i1 = 0;
            int i2 = 0;

            for (int i = 0; i < result.length; i++) {
                if (i2 >= right.length || (i1 < left.length && left[i1].compareTo(right[i2]) <= 0)) {
                    result[i] = left[i1];
                    i1++;
                } else {
                    result[i] = right[i2];
                    i2++;
                }
            }
        }
    }
}
