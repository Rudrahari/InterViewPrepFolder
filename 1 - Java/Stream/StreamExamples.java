package Stream;

import java.util.*;
import java.util.stream.*;

public class StreamExamples {
    public static void main(String[] args) {

        List<Integer> listOfNumbers = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        //Usual way of filtering with loops
        int countGreater = 0;
        for (int num : listOfNumbers) {
            if (num > 5) {
                countGreater++;
            }
        }
        System.out.println(countGreater);

        // Stream Creation -> Intermediate Operation -> Terminal Operation
        // Stream Creation -> Create a Stream from a collection of data
        // Intermediate Operation-> Converts the Stream to another stream
        // based upon the intermediate operation we have utilized
        // Can have zero or more intermediate operation
        // Terminal Operation-> returns the result
        // only one allowed per stream
        // stream will start running when the terminal operation is invoked

        //Above block of code replaced with streams
        long streamsCount = listOfNumbers.stream().filter((val) -> val > 5).count();
        System.out.println(streamsCount);

        //No intermediate operations
        long streamsCountWithNoIntermediate = listOfNumbers.stream().count();
        System.out.println(streamsCountWithNoIntermediate);

        //Some ways of creating a stream

        // 1. Collection Interface - default method stream()
        Stream<Integer> collectionStreams = listOfNumbers.stream();

        // 2. Arrays Class - static method stream(T[] t)
        Integer[] intArray = new Integer[]{1, 2, 3, 4, 5};
        Stream<Integer> arrayStreams =
                Arrays.stream(intArray);

        // 3. Stream interface - static method of(varargs..... )
        Stream<Integer> streamMethod = Stream.of(1, 2, 3, 4, 5, 6, 7);

        // 4. Stream Builder
        Stream.Builder<Integer> streamBuilder = Stream.builder();
        streamBuilder.add(100).add(120).add(140);
        Stream<Integer> finalStream = streamBuilder.build();

        // 5. Stream iterate
        Stream<Integer> streamIterate = Stream.iterate(100, (num) -> num + 100).limit(10);

        // 6. Stream generate
        Stream<Integer> streamGenerate = Stream.generate(() -> 25).limit(10);

        // Intermediate operations in action

        listOfNumbers = new ArrayList<Integer>(List.of(11, 11, 22, 32, 41, 59, 63, 72, 86, 92, 170));
        List<String> list = new ArrayList<>(List.of("stream", "intermediate", "terminal"));
        List<List<String>> listOfList = new ArrayList<>(List.of(list));
        // 1. filter - takes in a Predicate
        // filters out based on the given condition
        Stream<Integer> evenNums = listOfNumbers
                .stream()
                .filter((num) -> num % 2 == 0);
        List<Integer> evenList = evenNums.collect(Collectors.toList());
        System.out.println(evenList);

        // 2. map - takes in a function
        // transforms each stream element
        List<String> evenOrOddList = listOfNumbers
                .stream()
                .map((num) -> num % 2 == 0 ? "Even" : "Odd").toList(); //can also use toList()
        System.out.println(evenOrOddList);

        // 3. flatMap - flattens the stream
        // list of list can be converted to list
        List<String> flatListofList = listOfList
                .stream()
                .flatMap((listForFlat) -> listForFlat.stream())
                .collect(Collectors.toList());
        System.out.println(flatListofList);

        // combining multiple intermediate operations
        flatListofList = listOfList
                .stream()
                .flatMap(Collection::stream) //method reference for the above example
                .map(String::toUpperCase) // method reference for string.toUpperCase()
                .collect(Collectors.toList());
        System.out.println(flatListofList);

        //combining operations inside a intermediate operation
        List<Character> flatList = list
                .stream()
                .flatMap((str) -> str.chars().mapToObj((ch) -> (char) ch))
                .toList();
        System.out.println(flatList);

        String flatString = list
                .stream()
                .collect(Collectors.joining("-"));
        System.out.println(flatString);

        // 4. distinct - remove any duplications present in the upstream
        List<Integer> uniqueElements = listOfNumbers
                .stream()
                .distinct()
                .toList();
        System.out.println(uniqueElements);

        // 5. sorted - sorts the stream
        List<Integer> sortedElements = listOfNumbers
                .stream()
                .sorted()
                .toList();
        System.out.println(sortedElements);
        sortedElements = listOfNumbers
                .stream()
                .sorted((a, b) -> b - a)
                .toList();
        System.out.println(sortedElements);

        // 6. peek - logs the result from intermediate operation
        // below is a good example for stream lazy behavior
        // for a stream of { 1, 2} the outtput will be as below
        // before map:1 after map: 1 before map: 2 after map: 2
        sortedElements = listOfNumbers
                .stream()
                .sorted((a, b) -> b - a)
                .peek((a) -> System.out.print("before map: " + a))
                .map((num) -> num / 10)
                .peek((val) -> System.out.print("after map: " + val))
                .toList();
        System.out.println();
        System.out.println(sortedElements);

        // 7. limit - limits the elements present
        List<Integer> firstFiveSortedElements = listOfNumbers
                .stream()
                .sorted((a, b) -> b - a)
                .limit(5)
                .toList();
        System.out.println(firstFiveSortedElements);

        // 8. skip - skips the first n elements
        List<Integer> skipFirstFive = listOfNumbers
                .stream()
                .skip(5)
                .toList();
        System.out.println(skipFirstFive);

        // 9. mapToInt - works with int primitive data type stream (IntStream)
        List<Character> characters = List.of('a', 'b', 'c', 'd');
        IntStream intStream = characters.stream().mapToInt((ch) -> (int) ch);
        int[] characterAscii = intStream.toArray();
        System.out.println(Arrays.toString(characterAscii));

        // 10. mapToDouble
        List<String> doubleNumbers = List.of("12.345", "456.894");
        DoubleStream doubleStream = doubleNumbers.stream().mapToDouble((str) -> Double.parseDouble(str));
        double[] doubleArray = doubleStream.toArray();
        System.out.println(Arrays.toString(doubleArray));

        // 10. mapToDouble
        int[] intArrays = new int[]{1, 2, 3, 4, 5};
        // using int - primitive data types in lambda params
        LongStream longStream = Arrays.stream(intArrays).mapToLong((int val) -> (long) val * 2);
        long[] longStreamArray = longStream.toArray();
        System.out.println(Arrays.toString(longStreamArray));


        // Sequential processing of stream
        // understanding the output from the below code help in understanding the sequential flow of stream
        // remember in some intermediate operations(such as sorted) all the elements needs to be processed before moving on to the next stream operation
        // output: after sorted: 170after sorted: 92after sorted: 86after sorted: 72after sorted: 63after sorted: 59after skip: 59after map: 69after sorted: 41after skip: 41after map: 51after sorted: 32after skip: 32after map: 42after sorted: 22after skip: 22after map: 32after sorted: 11after skip: 11after map: 21after sorted: 11after skip: 11after map: 21
        List<Integer> processedElements = listOfNumbers
                .stream()
                .sorted((a, b) -> b - a)
                .peek((a) -> System.out.print("after sorted: " + a))
                .skip(5)
                .peek((val) -> System.out.print("after skip: " + val))
                .map((num) -> num + 10)
                .peek((val) -> System.out.print("after map: " + val))
                .toList();
        System.out.println();
        System.out.println(processedElements);

        // Terminal operations in action

        // 1. forEach - iterate through the stream elements
        List<String> allStringValues = List.of("abcd", "efgh", "ijklmnopq");
        allStringValues
                .stream()
                .filter((str) -> str.length() > 4)
                .forEach(System.out::println);

        // 2. toArray - returns object array
        Object[] array = Arrays.stream(intArray).toArray();
        System.out.println(Arrays.toString(array));

        Integer[] newArray = (Integer[]) Arrays
                .stream(intArray)
                .filter((num) -> num % 2 == 0)
                //.toArray((int size)-> new Integer[size]);
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(newArray));

        // 3. reduce - combines them

        Optional<Integer> addNumbers = Arrays
                .stream(intArray)
                .reduce((a, b) -> a + b);
        System.out.println(addNumbers);

        Integer addNumbersReduce = Arrays
                .stream(intArray)
                .reduce(100, (a, b) -> a + b); //identity params for initial value
        System.out.println(addNumbersReduce);

        //4. collect - collects the element of the stream

        List<Integer> collectValues = Arrays
                .stream(intArray)
                .filter((num) -> num > 0)
                .collect(Collectors.toList());

        System.out.println(collectValues);

        // 5. min - picks up fist element from stream - pass a comparator to arrange them in some order
        // max - picks up last element from stream -  pass a comparator to arrange them in some order
        // As we are ordering them in some order, contextually the first element is min, last is max

        Optional<Integer> minimumAsc = Arrays
                .stream(intArray)
                .min(Comparator.naturalOrder());
        Optional<Integer> minimumDesc = Arrays
                .stream(intArray)
                .min((val1, val2) -> val2 - val1);
        Optional<Integer> maximumAsc = Arrays
                .stream(intArray)
                .max(Comparator.naturalOrder());
        Optional<Integer> maximumDesc = Arrays
                .stream(intArray)
                .max((val1, val2) -> val2 - val1);

        System.out.println(minimumAsc.get());
        System.out.println(minimumDesc.get());
        System.out.println(maximumAsc.get());
        System.out.println(maximumDesc.get());

        // 6. count - counts the stream elements
        long streamElements = Arrays
                .stream(intArray)
                .filter((num) -> num > 10)
                .count();
        System.out.println(streamElements);

        // 7. anyMatch - returns boolean if any elements matches the given predicate condition
        // allMatch - returns true if all is evaluated as per the condition
        // noneMatch - returns true is no stream element matches that condition
        boolean match = Arrays
                .stream(intArray)
                .anyMatch((num) -> num > 0);

        System.out.println(match);

        match = Arrays
                .stream(intArray)
                .allMatch((num) -> num > 0);
        System.out.println(match);

        match = Arrays
                .stream(intArray)
                .noneMatch((num) -> num > 2);
        System.out.println(match);

        // 8. findFirst - returns first, findAny - random element (parallel systems)
        Optional<Integer> findEle = Arrays
                .stream(intArray)
                .findFirst();
        System.out.println(findEle.get());

        Optional<Integer> findRandEle = Arrays
                .stream(intArray) // short-circuiting terminal operations as it will return it found right away
                .findAny(); // will always return first as it found the element 1 first
        System.out.println(findRandEle.get());

        // Stream once invoked by a terminal operation cannot be reused
        Stream<Integer> reuseStream = Arrays
                .stream(intArray);
        reuseStream.forEach(System.out::print);
//        List<Integer> c=reuseStream.toList(); will throw error as it's already consumed \

        // Parallel Stream

        long sequentialStreamTime = System.currentTimeMillis();
        listOfNumbers
                .stream()
                .sorted((a, b) -> b - a)
                .peek((a) -> System.out.print("after sorted: " + a))
                .skip(5)
                .peek((val) -> System.out.print("after skip: " + val))
                .map((num) -> num + 10)
                .peek((val) -> System.out.print("after map: " + val))
                .forEach(System.out::print);
        System.out.println();
        System.out.println("Sequential Stream Processing Time:" + (System.currentTimeMillis() - sequentialStreamTime));

        long parallelStreamTime = System.currentTimeMillis();
        listOfNumbers
                .parallelStream()
                .sorted((a, b) -> b - a)
                .peek((a) -> System.out.print("after sorted: " + a))
                .skip(5)
                .peek((val) -> System.out.print("after skip: " + val))
                .map((num) -> num + 10)
                .peek((val) -> System.out.print("after map: " + val))
                .forEach(System.out::print);
        System.out.println();
        System.out.println("Parallel Stream Processing Time:" + (System.currentTimeMillis() - parallelStreamTime));

    }
}
