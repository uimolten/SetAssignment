/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2), 
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: rrt873
 *  email address: rrt873@my.utexas.edu
 *  TA name: Jason
 *  
 *  Student 2 
 *  UTEID:
 *  email address:   
 */

import java.util.Iterator;

/**
 * Students are to complete this class.
 * Students should implement as many methods
 * as they can using the Iterator from the iterator
 * method and the other methods.
 *
 */
public abstract class AbstractSet314<E> implements ISet<E> {

    /*
     * DELETE THIS COMMENT FROM YOUR SUBMISSION.
     * 
     * RECALL:
     * 
     * NO INSTANCE VARIABLES ALLOWED.
     * 
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * 
     * NO DIRECT REFERENCES to ArrayList or other Java Collections.
     * 
     * NO METHODS ADDED other than those in ISet and Object.
     */

    public boolean contains(E obj) {
        for (E val : this) {
            if (val.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        int count = 0;
        for (E val : this) {
            count++;
        }
        return count;
    }

    public boolean containsAll(ISet<E> otherSet) {
        for (E val : otherSet) {
            if (!this.contains(val)) {
                return false;
            }
        }
        return true;
    }

    public boolean remove(E item) {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(item)) {
                it.remove();
                return true;
            }
            it.next();
        }

        return false;
    }

    public void clear() {
        for (E item : this) {
            this.remove(item);
        }
    }

    public boolean addAll(ISet<E> otherSet) {
        boolean changed = false;
        for (E val : otherSet) {
            if (this.add(val)) {
                changed = true;
            }
        }
        return changed;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ISet<?>)) {
            return false;
        }

        ISet<E> otherSet = (ISet<E>) other;
        if (this.size() == otherSet.size()) {
            return this.containsAll(otherSet);
        }
        return false;
    }

    public ISet<E> difference(ISet<E> otherSet) {
        return difference(otherSet);
    }

    /**
     * Return a String version of this set.
     * Format is (e1, e2, ... en)
     * 
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}
