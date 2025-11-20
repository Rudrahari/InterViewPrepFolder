package Collections;

import java.util.List;

public class WildCardExample<T extends List> {
    public WildCardExample() {

    }
    public WildCardExample(T obj){
        System.out.println(obj);
    }
}
