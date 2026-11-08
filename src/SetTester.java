
/*
 * Student information for assignment:
 *
 * Number of slip days used:
 * Student 1 (Student whose turnin account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *
 * Student 2
 *  UTEID:
 *  email address:
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet314 class:
 * 
 * It is unwise to implement all three of the methods because we can write them all in terms of 
 * other existing methods. For example, union is just the addAll, and intersection can be written 
 * in terms of differnces. A intersection B is just A-(A-B).
 * 
 *
 * 
 * Order of ProcessText methods:
 * Each processText method processes N words and calls add for each word.
 *   UnsortedSet and SortedSet have O(M) worst case additions, giving O(N*M) total time.
 *  HashSet has  O(1) additions, giving O(N) total time. 
 * TreeSet has O(log M) additions, giving O(N log M) total time. 
 * 
 * 
 *  Orders of add methods:  
 * The big O for both sorted Setand unSOrted setr are O(N). However the Hash set add is O(1) and 
 * TreeSet add is O(Log n) (because it has to place the element in the right place using a binary
 * search approach)
 * 
 * 
 * Difference betwen Hashset output and treeset Output: 
 * The output of hashset isn't sorted, while 
 * treeSet is in Lexographical order
 * 
 *
 * Sorted Set:
 * File           Size (kB) Size Ratio Total Words Word Ratio Unique Words Unique Ratio Time (sec) Time Ratio
 * pg79922.txt    29.420     -          4667        -          1573         -            0.0345292   -
 * pg1513.txt     169.504    5.76x      29005       6.21x      6957         4.42x        0.4555142   13.19x
 * pg30249.txt    424.468    2.50x      74363       2.56x      12696        1.82x        1.4539359   3.19x
 * pg1727.txt     717.782    1.69x      132609      1.78x      14221        1.12x        3.1379792   2.16x
 *
 * Unsorted Set:
 * File           Size (kB) Size Ratio Total Words Word Ratio Unique Words Unique Ratio Time (sec) Time Ratio
 * pg79922.txt    29.420     -          4667        -          1573         -            0.0127539   -
 * pg1513.txt     169.504    5.76x      29005       6.21x      6957         4.42x        0.2343915   18.38x
 * pg30249.txt    424.468    2.50x      74363       2.56x      12696        1.82x        0.5669518   2.42x
 * pg1727.txt     717.782    1.69x      132609      1.78x      14221        1.12x        1.0225275   1.80x
 *
 * Hash Set:
 * File           Size (kB) Size Ratio Total Words Word Ratio Unique Words Unique Ratio Time (sec) Time Ratio
 * pg79922.txt    29.420     -          4667        -          1573         -            0.0034753   -
 * pg1513.txt     169.504    5.76x      29005       6.21x      6957         4.42x        0.0171007   4.92x
 * pg30249.txt    424.468    2.50x      74363       2.56x      12696        1.82x        0.0242487   1.42x
 * pg1727.txt     717.782    1.69x      132609      1.78x      14221        1.12x        0.0456632   1.88x
 *
 * Tree Set:
 * File           Size (kB) Size Ratio Total Words Word Ratio Unique Words Unique Ratio Time (sec) Time Ratio
 * pg79922.txt    29.420     -          4667        -          1573         -            0.0033009   -
 * pg1513.txt     169.504    5.76x      29005       6.21x      6957         4.42x        0.0180494   5.47x
 * pg30249.txt    424.468    2.50x      74363       2.56x      12696        1.82x        0.0311779   1.73x
 * pg1727.txt     717.782    1.69x      132609      1.78x      14221        1.12x        0.0672622   2.16x
 * 
 * 

 * 
 *
 */

public class SetTester {

    public static void main(String[] args) {

        ISet<String> s1 = new UnsortedSet<>();
        s1.add("ABC");
        s1.add("CBA");
        s1.add("MIKE");
        s1.add("BOGO");

        // test 1
        boolean actual = s1.contains("BOGO");
        showTestResults(actual, true, 1, s1, null, "add and contains methods UnsortedSet"
                + "/nset 1 contains BOGO.");

        // test 2
        s1.remove("BOGO");
        actual = s1.contains("BOGO");
        showTestResults(actual, false, 2, s1, null, "remove and contains method UnsortedSet"
                + "/nset1 does not contain BOGO.");

        // test 3
        actual = s1.size() == 3;
        showTestResults(actual, true, 3, s1, null, "size method UnsortedSet"
                + "/nsize of set 1 is 3.");

        ISet<String> s2 = new UnsortedSet<>();
        s2.add("ABC");
        s2.add("CBA");
        s2.add("MIKE");

        // test 4
        actual = s2.containsAll(s1);
        showTestResults(actual, true, 4, s1, s2, "containsAll method UnsortedSet"
                + "/ns2 contains all of s1.");

        // test 5
        ISet<String> s3 = s2.difference(s1);
        ISet<String> expected = new UnsortedSet<>();
        actual = s3.equals(expected);
        showTestResults(actual, true, 5, s1, s2, "difference and equals methods UnsortedSet"
                + "/ns2.difference(s1). result = " + s3 + " expected result = " + expected);

        // test 6
        s3 = s2.union(s1);
        expected.add("MIKE");
        expected.add("ABC");
        expected.add("CBA");
        actual = s3.equals(expected);
        showTestResults(actual, true, 6, s1, s2, "union and equals methods UnsortedSet"
                + "/ns2.union(s1). actual result = " + s3
                + " expected result = " + expected);

        // test 7
        s3 = s2.intersection(s1);
        actual = s3.equals(expected);
        showTestResults(actual, true, 7, s1, s2, "intersection and equals methods UnsortedSet"
                + "/ns2.intersection(s1). actual result = " + s3
                + " expected result = " + expected);

        // test 8: iterator visits every element exactly once, in any order.
        ArrayList<String> remaining = new ArrayList<>();
        remaining.add("ABC");
        remaining.add("CBA");
        remaining.add("MIKE");
        Iterator<String> unsortedIterator = s1.iterator();
        boolean iteratorCorrect = true;
        while (unsortedIterator.hasNext()) {
            if (!remaining.remove(unsortedIterator.next())) {
                iteratorCorrect = false;
            }
        }
        actual = iteratorCorrect && remaining.isEmpty();
        showTestResults(actual, true, 8, s1, null, "iterator method UnsortedSet"
                + "\nVisits every element exactly once without requiring an order.");

        // tests 9 and 10: clear for UnsortedSet and SortedSet.
        for (int testNumber = 9; testNumber <= 10; testNumber++) {
            ISet<String> clearSet = testNumber == 9
                    ? new UnsortedSet<>() : new SortedSet<>();
            clearSet.add("A");
            clearSet.add("B");
            clearSet.add("C");
            clearSet.clear();
            boolean emptied = clearSet.size() == 0
                    && !clearSet.iterator().hasNext()
                    && !clearSet.contains("A")
                    && !clearSet.contains("B")
                    && !clearSet.contains("C");
            clearSet.clear(); // Clearing an already empty set must also work.
            boolean stillEmpty = clearSet.size() == 0;
            boolean reusable = clearSet.add("A")
                    && clearSet.size() == 1 && clearSet.contains("A");
            actual = emptied && stillEmpty && reusable;
            showTestResults(actual, true, testNumber, clearSet, null,
                    "clear method " + (testNumber == 9 ? "UnsortedSet" : "SortedSet")
                            + "\nEmpties the set, handles an empty set, and permits reuse.");
        }

        // test 11: empty constructor.
        SortedSet<Integer> emptySorted = new SortedSet<>();
        actual = emptySorted.size() == 0 && !emptySorted.iterator().hasNext();
        showTestResults(actual, true, 11, emptySorted, null,
                "SortedSet constructor creates an empty set.");

        // test 12: copying an unsorted ISet sorts it and does not share storage.
        UnsortedSet<Integer> source = new UnsortedSet<>();
        source.add(8);
        source.add(-3);
        source.add(4);
        SortedSet<Integer> sortedCopy = new SortedSet<>(source);
        sortedCopy.add(12);
        actual = hasElementsInOrder(sortedCopy, -3, 4, 8, 12)
                && source.size() == 3 && !source.contains(12)
                && hasElementsInOrder(new SortedSet<Integer>(new UnsortedSet<Integer>()));
        showTestResults(actual, true, 12, sortedCopy, null,
                "SortedSet(ISet) sorts a copy, handles empty input, and preserves the source.");

        // test 13: insert at the beginning, middle, and end; reject duplicates.
        SortedSet<Integer> addSet = new SortedSet<>();
        boolean addedFirst = addSet.add(4);
        boolean addedSmallest = addSet.add(-3);
        boolean addedMiddle = addSet.add(1);
        boolean addedLargest = addSet.add(9);
        boolean addedDuplicate = addSet.add(4);
        actual = addedFirst && addedSmallest && addedMiddle && addedLargest
                && !addedDuplicate && hasElementsInOrder(addSet, -3, 1, 4, 9);
        showTestResults(actual, true, 13, addSet, null,
                "SortedSet add maintains order and reports duplicates.");

        // test 14: merge overlapping sets, accept unsorted input, and report changes.
        SortedSet<Integer> addAllSet = sortedSetOf(2, 6);
        SortedSet<Integer> addAllOther = sortedSetOf(1, 6, 9);
        boolean merged = addAllSet.addAll(addAllOther);
        boolean unchanged = !addAllSet.addAll(addAllOther)
                && !addAllSet.addAll(addAllSet)
                && !addAllSet.addAll(new SortedSet<Integer>());
        UnsortedSet<Integer> unsortedAdditions = new UnsortedSet<>();
        unsortedAdditions.add(8);
        unsortedAdditions.add(0);
        boolean mergedUnsorted = addAllSet.addAll(unsortedAdditions);
        actual = merged && unchanged && mergedUnsorted
                && hasElementsInOrder(addAllSet, 0, 1, 2, 6, 8, 9)
                && hasElementsInOrder(addAllOther, 1, 6, 9)
                && unsortedAdditions.size() == 2;
        showTestResults(actual, true, 14, addAllSet, addAllOther,
                "SortedSet addAll merges without duplicates or changing the other set.");

        // test 15: search boundaries, middle, gaps, and an empty set.
        SortedSet<Integer> containsSet = sortedSetOf(-4, 2, 10);
        actual = containsSet.contains(-4) && containsSet.contains(2)
                && containsSet.contains(10) && !containsSet.contains(-5)
                && !containsSet.contains(3) && !containsSet.contains(11)
                && !new SortedSet<Integer>().contains(2);
        showTestResults(actual, true, 15, containsSet, null,
                "SortedSet contains finds present elements and rejects missing elements.");

        // test 16: subsets need not have matching positions; trailing elements matter.
        SortedSet<Integer> containsAllSet = sortedSetOf(1, 3, 5, 7);
        UnsortedSet<Integer> unsortedSubset = new UnsortedSet<>();
        unsortedSubset.add(7);
        unsortedSubset.add(1);
        actual = containsAllSet.containsAll(sortedSetOf(3, 7))
                && containsAllSet.containsAll(unsortedSubset)
                && containsAllSet.containsAll(new SortedSet<Integer>())
                && containsAllSet.containsAll(containsAllSet)
                && !containsAllSet.containsAll(sortedSetOf(1, 3, 5, 7, 9))
                && !containsAllSet.containsAll(sortedSetOf(1, 4))
                && !new SortedSet<Integer>().containsAll(sortedSetOf(1));
        showTestResults(actual, true, 16, containsAllSet, null,
                "SortedSet containsAll handles subsets, missing tails, and empty sets.");

        // test 17: difference returns a new sorted set and preserves both inputs.
        SortedSet<Integer> differenceLeft = sortedSetOf(1, 3, 5, 9);
        SortedSet<Integer> differenceRight = sortedSetOf(0, 3, 8);
        ISet<Integer> differenceResult = differenceLeft.difference(differenceRight);
        actual = differenceResult instanceof SortedSet<?>
                && differenceResult != differenceLeft && differenceResult != differenceRight
                && hasElementsInOrder(differenceResult, 1, 5, 9)
                && hasElementsInOrder(differenceLeft, 1, 3, 5, 9)
                && hasElementsInOrder(differenceRight, 0, 3, 8)
                && hasElementsInOrder(differenceLeft.difference(differenceLeft))
                && hasElementsInOrder(differenceLeft.difference(new SortedSet<Integer>()),
                        1, 3, 5, 9);
        showTestResults(actual, true, 17, differenceLeft, differenceRight,
                "SortedSet difference keeps only left-side elements and preserves inputs.");

        // test 18: union includes leftovers, removes duplicates, and returns new storage.
        SortedSet<Integer> unionLeft = sortedSetOf(1, 4);
        SortedSet<Integer> unionRight = sortedSetOf(2, 4, 8);
        ISet<Integer> unionResult = unionLeft.union(unionRight);
        ISet<Integer> selfUnion = unionLeft.union(unionLeft);
        actual = unionResult instanceof SortedSet<?> && unionResult != unionLeft
                && unionResult != unionRight && hasElementsInOrder(unionResult, 1, 2, 4, 8)
                && selfUnion != unionLeft && hasElementsInOrder(selfUnion, 1, 4)
                && hasElementsInOrder(unionLeft, 1, 4)
                && hasElementsInOrder(unionRight, 2, 4, 8);
        unionResult.add(10);
        actual = actual && hasElementsInOrder(unionLeft, 1, 4)
                && hasElementsInOrder(unionRight, 2, 4, 8);
        showTestResults(actual, true, 18, unionLeft, unionRight,
                "SortedSet union returns an independent result without changing inputs.");

        // test 19: intersection keeps shared values, including empty and self cases.
        SortedSet<Integer> intersectionLeft = sortedSetOf(1, 3, 5, 9);
        SortedSet<Integer> intersectionRight = sortedSetOf(0, 3, 9, 12);
        ISet<Integer> intersectionResult = intersectionLeft.intersection(intersectionRight);
        ISet<Integer> selfIntersection = intersectionLeft.intersection(intersectionLeft);
        actual = intersectionResult instanceof SortedSet<?>
                && intersectionResult != intersectionLeft
                && intersectionResult != intersectionRight
                && hasElementsInOrder(intersectionResult, 3, 9)
                && selfIntersection != intersectionLeft
                && hasElementsInOrder(selfIntersection, 1, 3, 5, 9)
                && hasElementsInOrder(intersectionLeft.intersection(sortedSetOf(2, 4)))
                && hasElementsInOrder(intersectionLeft.intersection(new SortedSet<Integer>()))
                && hasElementsInOrder(intersectionLeft, 1, 3, 5, 9)
                && hasElementsInOrder(intersectionRight, 0, 3, 9, 12);
        showTestResults(actual, true, 19, intersectionLeft, intersectionRight,
                "SortedSet intersection returns only shared elements and preserves inputs.");

        // test 20: equality uses contents, including across sorted and unsorted sets.
        SortedSet<Integer> equalsSet = sortedSetOf(2, 5);
        UnsortedSet<Integer> equalUnsorted = new UnsortedSet<>();
        equalUnsorted.add(5);
        equalUnsorted.add(2);
        Object equalAsObject = sortedSetOf(5, 2);
        SortedSet<String> differentType = new SortedSet<>();
        differentType.add("2");
        differentType.add("5");
        actual = equalsSet.equals(equalsSet) && equalsSet.equals(equalAsObject)
                && equalsSet.equals(equalUnsorted) && equalUnsorted.equals(equalsSet)
                && !equalsSet.equals(sortedSetOf(2, 6))
                && !equalsSet.equals(sortedSetOf(2))
                && !equalsSet.equals(differentType) && !equalsSet.equals(null)
                && !equalsSet.equals(new ArrayList<Integer>())
                && new SortedSet<Integer>().equals(new UnsortedSet<String>());
        showTestResults(actual, true, 20, equalsSet, equalUnsorted,
                "SortedSet equals compares contents and rejects unequal or non-set objects.");

        // test 21: iterator returns each element once in ascending order.
        SortedSet<Integer> iteratorSet = sortedSetOf(9, -2, 4);
        Iterator<Integer> sortedIterator = iteratorSet.iterator();
        actual = true;
        int[] iteratorExpected = {-2, 4, 9};
        for (int value : iteratorExpected) {
            if (!sortedIterator.hasNext() || !Integer.valueOf(value).equals(sortedIterator.next())) {
                actual = false;
                break;
            }
        }
        actual = actual && !sortedIterator.hasNext()
                && !new SortedSet<Integer>().iterator().hasNext();
        showTestResults(actual, true, 21, iteratorSet, null,
                "SortedSet iterator visits exactly the expected ascending sequence.");

        // test 22: remove present and missing elements without disturbing the rest.
        SortedSet<Integer> removeSet = sortedSetOf(1, 3, 5, 7);
        boolean removedMiddle = removeSet.remove(3);
        boolean missingUnchanged = !removeSet.remove(6);
        actual = removedMiddle && missingUnchanged && hasElementsInOrder(removeSet, 1, 5, 7);
        boolean removedFirst = removeSet.remove(1);
        boolean removedLast = removeSet.remove(7);
        boolean removedRemaining = removeSet.remove(5);
        actual = actual && removedFirst && removedLast && removedRemaining
                && !removeSet.remove(5) && hasElementsInOrder(removeSet);
        showTestResults(actual, true, 22, removeSet, null,
                "SortedSet remove reports changes and handles first, middle, last, and empty.");

        // test 23: size counts distinct elements and reflects removals.
        SortedSet<Integer> sizeSet = new SortedSet<>();
        boolean initiallyEmpty = sizeSet.size() == 0;
        sizeSet.add(4);
        sizeSet.add(4);
        sizeSet.add(8);
        boolean distinctCount = sizeSet.size() == 2;
        sizeSet.remove(4);
        actual = initiallyEmpty && distinctCount && sizeSet.size() == 1;
        showTestResults(actual, true, 23, sizeSet, null,
                "SortedSet size counts distinct elements after additions and removals.");

        // test 24: min returns the smallest value without removing it.
        SortedSet<Integer> minSet = sortedSetOf(6, -5, 2);
        actual = minSet.min().equals(-5) && hasElementsInOrder(minSet, -5, 2, 6)
                && sortedSetOf(4).min().equals(4);
        showTestResults(actual, true, 24, minSet, null,
                "SortedSet min handles multiple elements and a singleton.");

        // test 25: max returns the largest value without removing it.
        SortedSet<Integer> maxSet = sortedSetOf(6, -5, 2);
        actual = maxSet.max().equals(6) && hasElementsInOrder(maxSet, -5, 2, 6)
                && sortedSetOf(-4).max().equals(-4);
        showTestResults(actual, true, 25, maxSet, null,
                "SortedSet max handles multiple elements and a singleton.");

        // CS314 Students. Uncomment this section when ready to
        // run your experiments
        // try {
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // }
        // catch(Exception e) {
        // System.out.println("Unable to change look and feel");
        // }
        // Scanner sc = new Scanner(System.in);
        // String response = "";
        // do {
        // largeTest();
        // System.out.print("Another file? Enter y to do another file: ");
        // response = sc.next();
        // } while( response != null && response.length() > 0
        // && response.substring(0,1).equalsIgnoreCase("y") );

    }

    // Build small fixtures for the SortedSet tests.
    private static SortedSet<Integer> sortedSetOf(int... values) {
        SortedSet<Integer> result = new SortedSet<>();
        for (int value : values) {
            result.add(value);
        }
        return result;
    }

    // Check exact contents and order without relying on equals or containsAll.
    private static boolean hasElementsInOrder(ISet<Integer> set, int... expectedValues) {
        Iterator<Integer> iterator = set.iterator();
        for (int value : expectedValues) {
            if (!iterator.hasNext() || !Integer.valueOf(value).equals(iterator.next())) {
                return false;
            }
        }
        return !iterator.hasNext() && set.size() == expectedValues.length;
    }

    // print out results of test
    private static <E> void showTestResults(boolean actualResult, boolean expectedResult,
            int testNumber, ISet<E> set1, ISet<E> set2, String testDescription) {
        if (actualResult == expectedResult) {
            System.out.println("Passed test " + testNumber);
        } else {
            System.out.print("Failed test ");
            System.out.println(testNumber + ": " + testDescription);
            System.out.println("Expected result: " + expectedResult);
            System.out.println("Actual result  : " + actualResult);
            System.out.println("Set 1: " + set1);
            if (set2 != null) {
                System.out.println("Set 2: " + set2);
            }
        }

    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. "
                + "You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
            int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set) {
                System.out.println(o);
            }
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evaluating the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            Scanner s = null;
            try {
                s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }

        return text.toString();
    }
}
