package OOP.Basics;

import java.util.List;

public class OOP {

    // when a class is loaded, static block is executed
    // it is executed in the order it is present in the class
    static{
        System.out.println("Static Block executed inside the block");
    }
    public static void main(String[] args){

        System.out.println("Main Block executed");
        //new keyword allocates memory to the object at run time and returns the reference to ot
        Player player1=new Player(1,"Barcelona","Football");
        Player player2=new Player(10,"Lakers","Basketball");

        //declares a variable of type Player, doesn't automatically allocates memory
        Player player;
        // allocates memory - new keyword
        player=new Player(8,"CSK","Cricket");

        // accessing member of the class using dot( . ) operator
        System.out.println(player.jerseyNo);

        Player nullPlayer=new Player();
        System.out.println(nullPlayer.jerseyNo);

        // storing reference of nullPlayer object
        Player refPlayer=nullPlayer;
        refPlayer.jerseyNo=-100;

        // -100 will be updated here as refPlayer, and nullPlayer stored the reference of same object
        System.out.println(nullPlayer.jerseyNo);

        Integer num=100;
        num++;
        System.out.println(num);

        final int number=1;
        //cannot change the value once it is assigned as it is final
        //number=2;

        final Player playerFinal=new Player();
        //can change the state of the object marked as final
        playerFinal.sport="Changed Sport";
        //cannot reassign it i.e, cannot change the reference which was intialized
        //playerFinal=new Player(1,"test","test");

        // creating and reassigning too many objects for demonstration of finalize method
//        for(long i=0;i<1000000000;i++){
//            nullPlayer=new Player();
//        }

        // Accessing static variable using class name
        System.out.println(Player.century);
        // can also be accessed via object but not recommended
        // as static value is saved in once place and accessible by all instances of a class
        System.out.println(player.century);

        // have added additional variable count to keep track of number of instances created from class
        System.out.println(Player.totalPlayers); // no of instances we created from class Player


        SingletonClass one=SingletonClass.getInstance();
        SingletonClass two=SingletonClass.getInstance();

        // number of times instance were created - 1
        System.out.println(SingletonClass.count);
    }

    // will not work
    // Java - is pass by value
    // whatever is swapped here, is the local variables not from the main method
    public static void swap(int a,int b){
        int temp =a;
        a=b;
        a=temp;
    }

    // will not work
    // Java - is pass by value i.e. even for object we just send the object reference
    // we have two new variables which is pointing to the respective value in heap
    // when we swap it just swaps the reference for local variables thats it
    // there is no way to change the state of the object, we just reassign another object
    // as Integer is immutable as well
    public static void swapInt(Integer a,Integer b){
        Integer temp=a;
        b=a;
        a=b;
    }

    // will work
    // as we change the state of the pointed reference which will also be reflected on main
    public static void workingMethod(int[] arg){}
    public static void workingMethod1(List<Integer> result){}

    // static, non-static examples
    // static - class dependent, non-static - object dependent
    // static, non-static can be invoked from a non-static method directly
    // non-static cannot be invoked directly from a static method
    static void staticMethod(){
        // nonStaticMethod(); - will not work, as it is an instance needs a instance for it
        OOP ex=new OOP();
        ex.nonStaticMethod(); // can be used by creating an instance
        System.out.println("End of Static Method");
    }

    void nonStaticMethod(){
        // can use a static method directly without creating an instance
        staticMethod();
        anotherNonStaticMethod();
        System.out.println("End of Non Static Method");
    }

    void anotherNonStaticMethod(){
        // can use a non-static method in another non-static method, as both depends on instance
        nonStaticMethod();
        System.out.println("End of Another Non Static Method");
    }
}
