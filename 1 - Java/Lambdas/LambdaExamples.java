import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class LambdaExamples {
    public static void main(String[] args) {
        //Passing interface as a param to use the filtering condition
        //Prior to Lambdas, the way in which we can pass a function(indirectly) as a param
        List<University> universities = new ArrayList<>();

        universities.add(new University("MIT", 12670));
        universities.add(new University("Stanford", 9800));
        universities.add(new University("Harvard", 16760));

        List<University> filteredUniversitiesGreater = filter(new FilteringGreater()
                , universities,
                15000);
        System.out.println(filteredUniversitiesGreater);

        List<University> filteredUniversitiesLesser = filter(new FilteringLesser()
                , universities,
                15000);
        System.out.println(filteredUniversitiesLesser);

        //Anonymous inner class for concise, less overhead code
        List<University> filteredUniversitiesLEquals = filter(
                new Filtering() {
                    @Override
                    public boolean filterBasedOnCondition(University university, int count) {
                        return university.getStudentCount() == count;
                    }
                }
                , universities,
                9800);
        System.out.println(filteredUniversitiesLEquals);

        //Lambda expression for the above filtering conditions
        List<University> filteredUniversitiesUsingLambda = filter(
                (University university, int count) -> {
                    return university.getStudentCount() >= count;
                },
                universities,
                10000
        );
        System.out.println(filteredUniversitiesUsingLambda);

        //with types in params
        List<University> filteredUniversitiesUsingLambda2 = filter(
                (University university, int count) -> university.getStudentCount() <= count,
                universities,
                10000
        );
        System.out.println(filteredUniversitiesUsingLambda2);

        //without types in params
        List<University> filteredUniversitiesUsingLambda3 = filter(
                (university, count) -> university.getStudentCount() == count,
                universities,
                10000
        );
        System.out.println(filteredUniversitiesUsingLambda3);

        //omit count in the params, used it just for practice
        //Same as the classes (FilteringGreater, FilteringLesser) which implements
        Filtering lambdaDefinition = (university, count) ->
                university.getUniversityName().equals("MIT")
                        && university.getStudentCount() > 0;

        List<University> filteredUniversitiesUsingLambda4 = filter(
                lambdaDefinition,
                universities,
                10000
        );
        System.out.println(filteredUniversitiesUsingLambda4);

        //Getting lambda from a method
        Predicate<University> lambdaFromMethod = getLambda();

        //Combining predicate
        Predicate<String> first = (param) -> param.length() > 1;
        Predicate<String> second = (param) -> param.length() == 3;
        Predicate<String> last = first.and(second);

        boolean finalAnswer=last.test("MIT");
        System.out.println(finalAnswer);

        last = first.or(second);
        finalAnswer=last.test("Harvard");
        System.out.println(finalAnswer);

        //Taken in some params and doesn't return anything, we can do some operation instead in it
        // used with streams,collections to perform processing
        Consumer<String> consumerExample= (val)-> System.out.println("Value: "+val);
        consumerExample.accept("Test");

        Consumer<University> universityConsumer= (university)-> System.out.println(university);
        universities.forEach(universityConsumer);

        //Method reference for the above example
        Consumer<University> universityConsumer1= System.out::println;
        universities.forEach(universityConsumer1);

        //Chaining consumer
        Consumer<Integer> firstChain=(param)-> System.out.println("First: "+param);
        Consumer<Integer> secondChain=(param)-> System.out.println("Second: "+param);

        Consumer<Integer> finalChain=firstChain.andThen(secondChain);
        finalChain.accept(123);

        //for primitive types - int, double etc..
        IntConsumer primitiveTypeInt= num -> System.out.println(num * 2);
        primitiveTypeInt.accept(23);

        //Supplier doesn't take any params,but return something of type we used

        Supplier<String> sampleSupplier=()-> "returnValue";
        System.out.println(sampleSupplier.get());

        //Function - Takes one type, returns another type
        Function<String,Integer> sampleFunction= (val)-> val.length();
        Integer length=sampleFunction.apply("Stanford");
        System.out.println(length);

        //Function - Compose
        //Passing one result to another
        Function<String,Integer> lengthFunction= (val)-> val.length()%2;
        Function<Integer,String> evenOrOddLength= num -> num%2==0?"Even":"Odd";
        Function<String, String> resultFunction=evenOrOddLength.compose(lengthFunction);
        System.out.println(resultFunction.apply("ABCD"));
    }

    //Predicate is a functional interface,which method takes one params and return boolean
    public static Predicate<University> getLambda() {
        //just for reference, not particularly linked with lambda functionality
        String universityName = "Stanford";
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        Predicate<University> lambda =
                university -> {
                    //universityName=universityName+"id";
                    numbers.add(1);
                    return university.getUniversityName().equals(universityName);
                };

        //explicitly for local variable
        // Objects, class variables are fine as we will be using the reference
        //below code will throw error as the local variable used will be effectively final
        //when used in a lambda expression/anonymous inner class
        //universityName=universityName+"id";

        //changing the reference will throw error in the lambda expression
        //same as class variables which was also been taken as a reference not in the way as a local variable
        //numbers=new ArrayList<>();
        return lambda;
    }

    //wrapping the function inside the interface to invoke it in reusable block of code
    //new class which implements interface to define filter logic
    //greater,lesser, equal to - separate class to define filtering logic
    //for filtering just for these three no need for writing three separate method
    // DRY(Don't Repeat Yourself)
    public static List<University> filter(Filtering filter,
                                          List<University> universities,
                                          int count) {
        List<University> filteredUniversities = new ArrayList<>();
        for (int i = 0; i < universities.size(); i++) {
            if (filter.filterBasedOnCondition(universities.get(i), count)) {
                filteredUniversities.add(universities.get(i));
            }
        }
        return filteredUniversities;
    }

}
