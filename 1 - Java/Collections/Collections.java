package Collections;

import java.util.*;

public class Collections {

    public static void main(String[] args) {

        //Creating custom int array class
        IntIterableArray intIterableArray = new IntIterableArray();
        intIterableArray.add(1);
        intIterableArray.add(2);
        intIterableArray.add(3);

        int arraySize = intIterableArray.getSize();
        for (int i = 0; i < arraySize; i++) {
            System.out.print(intIterableArray.getItemAtIndex(i));
        }
        System.out.println();
        //using our own iterator
        Iterator<Integer> intArrayIterator = intIterableArray.iterator();

        //custom iterator
        while (intArrayIterator.hasNext()) {
            System.out.println(intArrayIterator.next());
        }

        // we didn't implement( override ) forEach method, but as it's a default method in iterable interface
        // we can use them in enhanced for loop
        for (Integer val : intIterableArray) {
            System.out.println(val);
        }

        //For this kind of implementation in custom class
        //we need to know the data type beforehand
        //data type can also change over time, we cannot go on and change the data type
        GenericIterableArray<String> genericIterableArray = new GenericIterableArray<String>();
        genericIterableArray.add("One");
        genericIterableArray.add("Two");

        GenericIterableArray<Integer> genericIterableArray1 = new GenericIterableArray<Integer>();
        genericIterableArray1.add(1);
        genericIterableArray1.add(1);

        for (int i = 0; i < genericIterableArray.getSize(); i++) {
            System.out.print(genericIterableArray.getItemAtIndex(i));
        }
        System.out.println();

        //With generic iterator we didn't need to make any changes
        //as even with data type changes we can work with the same class object
        Iterator<String> genericIterator = genericIterableArray.iterator();
        while (genericIterator.hasNext()) {
            System.out.println(genericIterator.next());
        }
        Iterator<Integer> genericIterator1 = genericIterableArray1.iterator();
        while (genericIterator1.hasNext()) {
            System.out.println(genericIterator1.next());
        }
        for (String val : genericIterableArray) {
            System.out.println(val);
        }

        //Iterator can be used with collection interface as, collection implements iterable

        // List Interface
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        // making use of the iterator
        // list iterator provides methods for bidirectional traversal
        // next() - returns current element and increments the index
        // hasNext() - check if there is any element on the current index
        // previous() - decrements the current index and points to that index
        // hasPrevious() - checks if there is anything present on curIndex-1
        ListIterator iterator = list.listIterator();
        //starts with 1
        //points 1 in the beginning
        System.out.println(iterator.next()); // returns 1
        // points to 2
        System.out.println(iterator.next()); // returns 2
        // points past 2 as it's the last element
        // previous comes back to 2
        // points to 2
        System.out.println(iterator.previous()); // returns 2
        // points to 2
        // has previous points to previous element, which in our case is 1
        // current iterator still points to 2
        System.out.println(iterator.hasPrevious());// true - check if there is any previous element
        // points to 2
        System.out.println(iterator.hasNext()); // true - check if there is anything on current index
        // points to 2
        System.out.println(iterator.next()); // 2

        // adding all the elements from a list
        List<Integer> newListFromAboveList = new ArrayList<>();
        newListFromAboveList.addAll(list);

        // index based assigning
        newListFromAboveList.set(0, 12);
        System.out.println(newListFromAboveList);

        //iterator iterates from the gives index
        ListIterator itr = newListFromAboveList.listIterator(1);
        System.out.println(newListFromAboveList.get(1));

        newListFromAboveList.add(100);
        newListFromAboveList.add(900);
        System.out.println(newListFromAboveList);

        // subList which returns a view from the original list
        List<Integer> subList = newListFromAboveList.subList(1, 3);
        System.out.println(subList);

        //changes in subList will be updated on both
        subList.set(1, 22);
        System.out.println(subList);
        System.out.println(newListFromAboveList);

        subList.add(10101);
        System.out.println(subList);
        System.out.println(newListFromAboveList);

        // structural modification in the original will throw ConcurrentModificationException
        newListFromAboveList.add(101);
        System.out.println(newListFromAboveList);
        //System.out.println(subList); - becomes invalid after original list modification

        // finds last index of the given element, returns -1 if not found
        newListFromAboveList.add(101);
        int index = newListFromAboveList.lastIndexOf(101);
        System.out.println(newListFromAboveList);
        System.out.println(index);

        //Vector class - similar to arraylist
        // Thread safe, while arraylist is not
        Vector<Integer> vectorClass = new Vector<>();
        vectorClass.add(1);
        System.out.println(vectorClass);

        // LinkedList class
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);

        Integer[] linkedArray = linkedList.toArray(new Integer[0]);

        // Queue interface - FIFO
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());

        // Stack class - LIFO
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);

        System.out.println(stack.pop());

        // Deque interface - double ended queue
        // add, remove from head, tail
        Deque<Integer> doubleEndedQueue = new ArrayDeque<>();

        doubleEndedQueue.offerFirst(1);
        doubleEndedQueue.offerFirst(1);
        doubleEndedQueue.offerLast(2);

        System.out.println(doubleEndedQueue);

        doubleEndedQueue.pollFirst();
        doubleEndedQueue.pollLast();

        System.out.println(doubleEndedQueue);

        // Priority Queue interface
        // Elements will be arranged on a priority unlike queue which maintains the order
        PriorityQueue<Integer> integerPriorityQueue = new PriorityQueue<>();
        integerPriorityQueue.offer(10);
        integerPriorityQueue.offer(2);
        // Integer it's natural order
        while (!integerPriorityQueue.isEmpty()) {
            System.out.println(integerPriorityQueue.poll());
        }
        // using custom comparator class - to order in descending
        integerPriorityQueue = new PriorityQueue<>(new ComparatorExampleClass());
        integerPriorityQueue.offer(1);
        integerPriorityQueue.offer(178);
        integerPriorityQueue.offer(90);
        while (!integerPriorityQueue.isEmpty()) {
            System.out.println(integerPriorityQueue.poll());
        }

        // custom class which implements comparable
        // comparable - natural ordering
        PriorityQueue<ComparableExampleClass> comparableExample = new PriorityQueue<>();
        comparableExample.offer(new ComparableExampleClass(124));
        comparableExample.offer(new ComparableExampleClass(133));
        comparableExample.offer(new ComparableExampleClass(10));

        while (!comparableExample.isEmpty()) {
            System.out.println(comparableExample.poll());
        }

        // Comparator have higher precedence than Comparable.
        // i.e. if for a class which implements comparabale
        // also we pass comparator, comparator will be executed

        //same example we used before but we pass comparator
        // also Integer implements comparable
        integerPriorityQueue = new PriorityQueue<>((a, b) -> b - a); //lambdas
        integerPriorityQueue.offer(1);
        integerPriorityQueue.offer(2);

        while (!integerPriorityQueue.isEmpty()) {
            System.out.println(integerPriorityQueue.poll());
        }

        // for the above example with our custom class
        PriorityQueue<ComparableExampleClass> examplePq = new PriorityQueue<>(
                (a, b) -> {
                    System.out.println("Comparator is executed"); // run code to check this output on console
                    return b.getTotalmarks() - a.getTotalmarks();
                });

        examplePq.offer(new ComparableExampleClass(100));
        examplePq.offer(new ComparableExampleClass(1001));
        examplePq.offer(new ComparableExampleClass(901));
        while (!examplePq.isEmpty()) {
            System.out.println(examplePq.poll());
        }
        // Set interface
        // Set using HashSet class - random order
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(10);
        set.add(100);
        set.add(1001);
        set.add(10);
        System.out.println(set);

        Set<Integer> set1 = new HashSet<>();
        set1.add(1002);

        List<Integer> ele = List.of(1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 0);
        set.addAll(set1);
        // can take a collection as a param
        set.addAll(ele);
        System.out.println(set);

        // finds intersection
        ele = List.of(1, 2, 3, 4, 5);
        set.retainAll(ele);
        System.out.println(ele); // 1 2 3 4 5

        // passing another set in params
        set.retainAll(set1);
        set.add(1002);
        System.out.println(set); // would only have the common element 1002
        System.out.println(set.contains(1002)); // true

        // contains() method works for Integer as it has it's implemented hashcode and equals method
        // if we are to use our custom class in hash set we have to override those methods
        Set<SetCustomClassExample> setCustomClassExamples = new HashSet<>();
        setCustomClassExamples.add(new SetCustomClassExample(100));
        setCustomClassExamples.add(new SetCustomClassExample(100));
        System.out.println(setCustomClassExamples);

        System.out.println(setCustomClassExamples.contains(new SetCustomClassExample(100))); //true

        // LinkedHashset class - maintains order
        Set<Integer> linkedSet = new LinkedHashSet<>();
        linkedSet.add(9);
        linkedSet.addAll(ele);
        System.out.println(linkedSet);

        // Sorted Set interface
        // natural ordering
        SortedSet<Integer> sortedSet = new TreeSet<>();
        sortedSet.add(1);
        sortedSet.add(-1);
        System.out.println(sortedSet);

        //can pass a comparator for desc ordering
        sortedSet = new TreeSet<>((a, b) -> b - a);
        sortedSet.add(90);
        sortedSet.add(0);
        sortedSet.add(10);
        System.out.println(sortedSet);

        //passing custom classes
        //passing on a comparator for ordering descedning ordering of their totalMarks
        SortedSet<SetCustomClassExample> customSortedSet = new TreeSet<>(
                (first, second) -> second.getTotalMarks() - first.getTotalMarks()
        );
        customSortedSet.add(new SetCustomClassExample(901));
        customSortedSet.add(new SetCustomClassExample(900));
        customSortedSet.add(new SetCustomClassExample(100));
        System.out.println(customSortedSet);

        // additional first, last method functionalities
        System.out.println(sortedSet.first()); //90
        System.out.println(sortedSet.last());  //0

        // NavigableSet interface
        // additional functionalities pollFirst, pollLast, ceiling, floor, higher, lower

        List<Integer> elements = List.of(8, 2, 9, 0, 1, 2, 4, 5, 7, 3, 6);
        NavigableSet<Integer> navigableSet = new TreeSet<>(elements);

        System.out.println(navigableSet.pollFirst()); // 0
        System.out.println(navigableSet.pollLast()); // 0
        System.out.println(navigableSet);
        // strictly greater than
        System.out.println(navigableSet.higher(1)); // 2
        // strictly lower than
        System.out.println(navigableSet.lower(7)); //6
        // greater than or equal to
        System.out.println(navigableSet.ceiling(0)); // 1
        System.out.println(navigableSet.ceiling(1)); // 1
        // greatest less than or equal to
        System.out.println(navigableSet.floor(9)); // 8
        System.out.println(navigableSet.floor(8)); // 8

        //Map interface
        // HashMap doesn't maintain ordering of key-value pairs
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 3);
        map.put(3, 5);
        map.put(1, 0); // value gets updated
        map.put(0, 0);
        map.put(8, 9);
        System.out.println("HashMap");
        for (Map.Entry<Integer, Integer> val : map.entrySet()) {
            System.out.println("Key: " + val.getKey() + ", Value: " + val.getValue());
        }

        //int nonExistentKeyValueInMap=map.get(10);  //will throw null pointer exception
        boolean nonExistentLKeyValueFlagInMap = map.containsKey(10);
        System.out.println(nonExistentLKeyValueFlagInMap);
        int nonExistentValueInMap = map.getOrDefault(10, -1); // to avoid the above exception scenario
        System.out.println(nonExistentValueInMap);

        // key no present scenario
        if (map.get(1) != null) {
            // some logic
        } else {
            //some logic
        }

        // computeIfPresent , computeIfPresent - instead of checking for key is present or not
        // we will use these methods

        HashMap<Integer, Integer> computeExamples = new HashMap<>();
        computeExamples.put(1, 1);
        //returns the key if  key is present
        int value = computeExamples.computeIfAbsent(1, key -> key + 100);
        // returns the value after updating those key values in hashmap, if not present
        int value1 = computeExamples.computeIfAbsent(2, key -> key + 10);
        System.out.println(value);
        System.out.println(value1);
        System.out.println(computeExamples);

        value = computeExamples.computeIfPresent(2, (key, keyValue) -> key * keyValue);
        //below line throws error as we used the key in the compute function even when it's not there.
        //value1=computeExamples.computeIfPresent(0,(key,keyValue)->key+keyValue);
        System.out.println(value);

        // HashTable - Synchronized i.e, thread safe, while HashMap is not
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        hashtable.put(1, 1);
        hashtable.put(2, 3);
        System.out.println("HashTable");
        for (Map.Entry<Integer, Integer> val : hashtable.entrySet()) {
            System.out.println("Key: " + val.getKey() + ", Value: " + val.getValue());
        }

        // convert map to set for traversal
        Set<Map.Entry<Integer, Integer>> setFromMap = map.entrySet();
        for (Map.Entry<Integer, Integer> keyVals : setFromMap) {
            System.out.println(keyVals.getKey() + " - " + keyVals.getValue());
        }

        Set<Integer> keys = map.keySet();
        for (int val : keys) {
            System.out.println(val);
        }

        Collection<Integer> values = map.values();
        System.out.println(values);
        for (int val : values) {
            System.out.println(val);
        }

        // LinkedHashMap - maintains the order of insertion, unlike HashMap
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(9, 1);
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        linkedHashMap.put(3, 3);
        linkedHashMap.put(4, 4);
        System.out.println(linkedHashMap);

        // SortedMap - entries are maintained in natural ordering of the key
        // refer above map
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>(linkedHashMap);
        System.out.println(sortedMap.firstKey());
        System.out.println(sortedMap.lastKey());

        SortedMap<Integer, Integer> descSortedMap = new TreeMap<>((a, b) -> b - a);
        descSortedMap.put(10, 10);
        descSortedMap.put(100, 100);
        descSortedMap.put(50, 50);
        System.out.println(descSortedMap);

        // NavigableMap - SortedMap + additional navigable methods
        NavigableMap<Integer, Integer> map1 = new TreeMap<>();
        map1.put(1, 1);
        map1.put(0, 0);
        map1.put(9, 2);
        map1.put(10, 7);

        System.out.println(map1.firstEntry()); // 0=0
        System.out.println(map1.lastEntry());  // 10=7
        System.out.println(map1.pollFirstEntry());
        System.out.println(map1.pollLastEntry());
        System.out.println(map1); // {1=1, 9=2}

        // floor - returns entry of key which is greatest less than elements or equal to
        System.out.println(map1.floorEntry(10).getKey());
        // floor - returns entry of key which is greatest  than  or equal to
        System.out.println(map1.ceilingEntry(0).getKey());

    }
}
