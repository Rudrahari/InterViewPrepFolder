package OOP.Basics;

public class SingletonClass {
    static int count;

    private SingletonClass(){

    }

    private static SingletonClass instance;

    public static SingletonClass getInstance() {
        if(instance==null){
            instance=new SingletonClass();
            count++;
        }
        return instance;
    }
}
