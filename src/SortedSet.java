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
     * Create an empty SortedSet.
     * pre: none
     * post: Creates an empty set with elements maintained in ascending order.
     * Big O: O(1)
     */
    public SortedSet() {
        con = new ArrayList<>();
    }

    /**
     * Create a new SortedSet containing all elements of other.
     * The elements are arranged in ascending order.
     * pre: other != null
     * post: Creates a sorted copy of other. Other is not altered.
     * Big O: O(N log N)
     */
    // TODO: preconditoosn
    public SortedSet(ISet<E> other) {
        con = new ArrayList<>();
        for (E val : other) {
            con.add(val);
        }
        mergeSort(con);
    }

    /**
     * Sort the elements of an ArrayList in ascending order
     * using the merge sort algorithm.
     * pre: data != null
     * post: All elements in data are sorted in ascending order.
     * Big O: O(N log N)
     */
    private void mergeSort(ArrayList<E> data) {
        ArrayList<E> temp = new ArrayList<>(data.size());

        // initialize each value to a null placeholder, since these values are only
        // changed, not
        // accessed.
        while (temp.size() < data.size()) {
            temp.add(null);
        }

        sort(data, temp, 0, temp.size() - 1);
    }

    /**
     * Recursively divide and sort a specified range of an ArrayList.
     * pre: data != null, temp != null, and low and high
     * define a valid range or an empty range.
     * post: The specified range is sorted in ascending order.
     * Big O: O(N log N)
     */
    private void sort(ArrayList<E> data, ArrayList<E> temp, int low, int high) {
        if (low < high) {
            int center = (low + high) / 2;
            sort(data, temp, low, center);
            sort(data, temp, center + 1, high);
            merge(data, temp, low, center + 1, high);
        }
    }

    /**
     * Merge two sorted portions of an ArrayList
     * into one
     * pre: data != null, temp != null, both portions
     * are sorted in ascending order.
     * post: The combined portion is sorted in ascending order.
     * Big O: O(N)
     */
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

    /**
     * Add an item to this SortedSet keeping ascending order
     * pre: item != null
     * post: The item is added if it was not already present.
     * return: true if this set changed, false otherwise.
     * Big O: O(N)
     */
    public boolean add(E item) {
        if (con.contains(item)) {
            return false;
        }

        for (int i = 0; i < con.size(); i++) {
            if (item.compareTo(con.get(i)) < 0) {
                con.add(i, item);
                return true;
            }
        }
        // biggest element present, add to the end
        con.add(item);
        return true;
    }

    /**
     * Add all elements of otherSet that are not already present
     * in this SortedSet.
     * pre: otherSet != null
     * post: This set contains all unique elements from both sets
     * in ascending order. OtherSet is not altered.
     * return: true if this set changed, false otherwise.
     * Big O: O(N) if both sets are sorted, O(N log N) if otherSet needs to be
     * sorted
     */
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

    /**
     * Create a new SortedSet containing the elements that are
     * present in this set but not in otherSet.
     * pre: otherSet != null
     * post: Returns the difference of the two sets.
     * Neither original set is altered.
     * return: A new set containing the difference.
     * Big O: O(N) if both sets are sorted,
     * O(N log N) if otherSet requires sorting.
     */
    public ISet<E> difference(ISet<E> otherSet) {
        SortedSet<E> result = new SortedSet<>();

        if (this == otherSet) {
            return result;
        }

        SortedSet<E> otherSortedSet;
        if (!(otherSet instanceof SortedSet<?>)) {
            otherSortedSet = new SortedSet<>(otherSet);
        } else {
            otherSortedSet = (SortedSet<E>) otherSet;
        }

        // Two Pointers approach
        result.con = diffMerger(otherSortedSet);

        return result;
    }

    /**
     * Create a new SortedSet containing all unique elements
     * in either this set or otherSet.
     * pre: otherSet != null
     * post: Returns the union of both sets in ascending order.
     * Neither original set is altered.
     * return: A new set containing the union.
     * Big O: O(N) if both sets are sorted,
     * O(N log N) if otherSet requires sorting.
     */
    public ISet<E> union(ISet<E> otherSet) {
        SortedSet<E> result = this;

        if (result == otherSet) {
            return otherSet;
        }

        result.addAll(otherSet);
        return result;
    }

    /**
     * Create a new SortedSet containing only the elements
     * that are present in both this set and otherSet.
     * pre: otherSet != null
     * post: Returns the intersection of both sets
     * in ascending order. Neither original set is altered.
     * return: A new set containing the intersection.
     * Big O: O(N) if both sets are sorted,
     * O(N log N) if otherSet requires sorting.
     */
    // TODO: Intersection
    public ISet<E> intersection(ISet<E> otherSet) {
        ISet<E> result = new SortedSet<>();
        result = this.difference(this.difference(otherSet));
        return result;
    }

    /**
     * Create an ArrayList containing elements present in this
     * set but not in otherSet using two-pointer traversal.
     * pre: none. private method; sets != null
     * post: Returns the difference in ascending order.
     * Neither original set is altered.
     * return: An ArrayList containing the difference.
     * Big O: O(N)
     */
    // TODO: maybe this can combine with the setMerger; only difference is the last
    // step
    private ArrayList<E> diffMerger(SortedSet<E> otherSet) {
        ArrayList<E> result = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;

        while (index1 < con.size() && index2 < otherSet.con.size()) {

            int compare = con.get(index1).compareTo(otherSet.con.get(index2));

            if (compare < 0) {
                // other doesn't have this value and since sorted, cant appear any later
                result.add(con.get(index1));
                index1++;
            } else if (compare > 0) {
                // other will never match, currently too small. move index2
                index2++;
            } else {
                // they are equal, not a difference since they are the same, move to next
                // for both
                index1++;
                index2++;
            }
        }

        // get the last elements left over in first set, if any
        while (index1 < con.size()) {
            result.add(con.get(index1));
            index1++;
        }
        return result;
    }

    /**
     * Merge two sorted sets into one ArrayList containing
     * all unique elements from both sets in ascending order.
     * pre: none. private method; sets != null
     * index1 and index2 are valid starting positions.
     * post: Returns an ArrayList containing the merged
     * elements. Neither original set is altered.
     * return: An ArrayList containing the merged elements.
     * Big O: O(N)
     */
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

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SortedSet<?>)) {
            return false;
        }
        SortedSet<E> otherSet = (SortedSet<E>) other;
        if (this.size() == otherSet.size()) {
            for (int i = 0; i < this.size(); i++) {
                if (!(this.con.get(i).equals(otherSet.con.get(i)))) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Return an Iterator over the elements of this SortedSet
     * pre: none
     * return: An Iterator for the elements of this set
     * Big O: O(1)
     */
    public Iterator<E> iterator() {
        return con.iterator();
    }

    /**
     * Return the size of this SortedSet
     * pre: none
     * return: an int representing the size of the Set
     */
    public int size() {
        return con.size();
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br>
     * pre: size() != 0
     *
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        if (size() == 0) {
            throw new IllegalStateException("List is empty. Cannot return min().");
        }

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
        if (size() == 0) {
            throw new IllegalStateException("List is empty. Cannot return min().");
        }

        return con.get(con.size() - 1);
    }

}