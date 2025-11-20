package Concurrency;

public class SingletonThreadSafe {
    //    Without synchronization and volatile, which can create multiple objects, but that's not allowed in singleton principle
//    when multiple thread pass through the condition or if the below variable is not yet updated it can happen
//    private static SingletonThreadSafe singletonThreadSafe=null;
//
//    private SingletonThreadSafe(){
//        System.out.println("Object created");
//    }
//
//    public static SingletonThreadSafe getSingletonThreadSafeInstance(){
//        if(singletonThreadSafe==null){
//            singletonThreadSafe=new SingletonThreadSafe();
//            return singletonThreadSafe;
//        }
//        return singletonThreadSafe;
//    }
    private static volatile SingletonThreadSafe singletonThreadSafe = null;

    private SingletonThreadSafe() {
        System.out.println("Object created");
    }

    public static SingletonThreadSafe getSingletonThreadSafeInstance() {
        if (singletonThreadSafe == null) {
            synchronized (SingletonThreadSafe.class) {
                if (singletonThreadSafe == null) {
                    singletonThreadSafe = new SingletonThreadSafe();
                }
            }

        }
        return singletonThreadSafe;
    }

    public static void main(String[] args) {

        Thread firstThread = new Thread(
                () -> {
                    SingletonThreadSafe obj;
                    for (int i = 0; i < 100; i++) {
                        obj = SingletonThreadSafe.getSingletonThreadSafeInstance();
                    }
                }
        );
        Thread secondThread = new Thread(
                () -> {
                    SingletonThreadSafe obj;
                    for (int i = 0; i < 100; i++) {
                        obj = SingletonThreadSafe.getSingletonThreadSafeInstance();
                    }
                }
        );

        firstThread.start();
        secondThread.start();
    }
}
