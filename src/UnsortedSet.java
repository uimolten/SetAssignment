/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2), 
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet314 and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet314<E> {

    private ArrayList<E> con;

    /**
     * Create an empty UnsortedSet.
     * pre: none
     * post: Creates an empty set with elements maintained in no particular order.
     * Big O: O(1)
     */
    public UnsortedSet() {
        con = new ArrayList<>();
    }

    /**
     * Add an item to this SortedSet keeping ascending order
     * pre: item != null
     * post: The item is added if it was not already present.
     * return: true if this set changed, false otherwise.
     * Big O: O(N)
     */
    public boolean add(E item) {
        if (!con.contains(item)) {
            return con.add(item);
        }
        return false;
    }

    /**
     * Remove all the elements in this UnsortedSet
     * pre: none
     * return: none, all elements of this unsorted set removed,
     * size is assigned 0.
     */
    public void clear() {
        while (con.size() > 0) {
            con.remove(con.size() - 1);
        }
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
     * Create a new UnsortedSet containing the elements that are
     * present in this set but not in otherSet
     * pre: otherSet != null
     * post: Returns the difference of the two sets.
     * Neither original set is changed
     * return: A new set containing the difference
     * Big O: O(N^2)
     */
    public ISet<E> difference(ISet<E> otherSet) {
        ISet<E> result = new UnsortedSet<>();
        if (this == otherSet) {
            return result;
        }
        for (E val : this) {
            if (!otherSet.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    /**
     * Create a new UnsortedSet containing all unique elements
     * in either this set or otherSet
     * pre: otherSet != null
     * post: Returns the union of both sets in no particular order
     * Neither original set is changed
     * return: A new set containing the union
     * Big O: O(N^2)
     */
    public ISet<E> union(ISet<E> otherSet) {
        ISet<E> result = this;
        result.addAll(otherSet);
        return result;
    }

    /**
     * Create a new UnsortedSet containing only the elements
     * that are present in both this set and otherSet
     * pre: otherSet != null
     * post: Returns the intersection of both sets
     * in ascending order. Neither original set is altered
     * return: A new set containing the intersection
     * Big O: O(N^2)
     */
    public ISet<E> intersection(ISet<E> otherSet) {
        ISet<E> result = new UnsortedSet<>();
        result = this.difference(this.difference(otherSet));
        return result;
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

}
