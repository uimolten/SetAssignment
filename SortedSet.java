/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2),
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID:
 *  email address:
 *  TA name:
 *
 *  Student 2
 *  UTEID:
 *  email address:
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet314
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet314<E> {

    private ArrayList<E> con;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        con = new ArrayList<>();
    }

    /**
     * Create a copy of other that is sorted.<br>
     * 
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
        con = new ArrayList<>();
        for (E val : other) {
            con.add(val);
        }
        mergeSort(con);
    }

    private void mergeSort(ArrayList<E> data) {
        ArrayList<E> temp = new ArrayList<>(data.size());
        sort(data, temp, 0, temp.size() - 1);
    }

    private void sort(ArrayList<E> data, ArrayList<E> temp, int low, int high) {
        if (low < high) {
            int center = (low + high) / 2;
            sort(data, temp, low, center);
            sort(data, temp, center + 1, high);
            merge(data, temp, low, center + 1, high);
        }
    }

    private void merge(ArrayList<E> data, ArrayList<E> temp, int leftPos, int rightPos,
            int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (data.get(leftPos).compareTo(data.get(rightPos)) <= 0) {
                temp.set(tempPos, data.get(leftPos));
                leftPos++;
            } else {
                temp.set(tempPos, data.get(rightPos));
                rightPos++;
            }
            tempPos++;
        }

        while (leftPos <= leftEnd) {
            temp.set(tempPos, data.get(leftPos));
            tempPos++;
            leftPos++;
        }
        while (rightPos <= rightEnd) {
            temp.set(tempPos, data.get(rightPos));
            tempPos++;
            rightPos++;
        }

        for (int i = 0; i < numElements; i++, rightEnd--) {
            data.set(rightEnd, temp.get(rightEnd));
        }

    }

    public boolean add(E item) {
        if (con.contains(item)) {
            return false;
        }

        for (int i = 0; i < con.size(); i++) {
            if (item.compareTo(con.get(i)) < 0) {
                con.add(i, item);
            }
        }
        return true;
    }

    public boolean addAll(ISet<E> otherSet) {
        SortedSet<E> otherSortedSet;

        if (!(otherSet instanceof SortedSet<?>)) {
            otherSortedSet = new SortedSet<>(otherSet);
        } else {
            otherSortedSet = (SortedSet<E>) otherSet;
        }
        ArrayList<E> result = setMerger(0, 0, otherSortedSet);
        if (con.size() == result.size()) {
            return false;
        } else {
            con = result;
            return true;
        }

    }

    public ISet<E> union(ISet<E> otherSet) {
        ISet<E> result = this;
        result.addAll(otherSet);
        return result;
    }

    private ArrayList<E> setMerger(int index1, int index2, SortedSet<E> other) {
        ArrayList<E> result = new ArrayList<>();
        while (index1 < con.size() && index2 < other.con.size()) {
            if (con.get(index1).compareTo(other.con.get(index2)) < 0) {
                result.add(con.get(index1));
                index1++;
            } else if (con.get(index1).compareTo(other.con.get(index2)) > 0) {
                result.add(other.con.get(index2));
                index2++;
            } else {
                result.add(con.get(index1));
                index1++;
                index2++;
            }

        }
        while (index1 < con.size()) {
            result.add(con.get(index1));
            index1++;
        }

        while (index2 < other.con.size()) {
            result.add(other.con.get(index2));
            index2++;
        }

        return result;
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        return con.get(0);

    }

    /**
     * Return the largest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the largest element in this SortedSet.
     */
    public E max() {
        return con.get(con.size() - 1);
    }

}
