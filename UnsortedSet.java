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

    public UnsortedSet() {
        con = new ArrayList<>();
    }

    public boolean add(E item) {
        if (!con.contains(item)) {
            return con.add(item);
        }
        return false;
    }

    public void clear() {
        while (con.size() > 0) {
            con.remove(con.size() - 1);
        }
    }

    public int size() {
        return con.size();
    }

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

    public ISet<E> union(ISet<E> otherSet) {
        ISet<E> result = this;
        result.addAll(otherSet);
        return result;
    }

    public ISet<E> intersection(ISet<E> otherSet) {
        ISet<E> result = new UnsortedSet<>();
        result = this.difference(this.difference(otherSet));
        return result;
    }

    public Iterator<E> iterator() {
        return con.iterator();
    }

}
