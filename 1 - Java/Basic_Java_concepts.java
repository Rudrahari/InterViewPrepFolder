import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Basic_Java_concepts {

    public static void print(int [] arr,int n){
        for(int i=0;i<n;i++){
            System.out.println(arr[i]);
        }
    }

    public static void stringMethodsBuilder(){
        StringBuilder sb= new StringBuilder("Fast");
        sb.append(" Builder");
        System.out.println(sb.toString());
    }

    public static void stringBufferMethod(){
        StringBuffer sb=new StringBuffer("Hello");
        sb.append(" Java");
        sb.insert(0,"Welcome");
        sb.reverse();
        System.out.print(sb.toString());
    }

    public static void main(String [] args){

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the value of n:");
        int n=scanner.nextInt();
        int []arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
        }
        print(arr,n);
        stringMethodsBuilder();//Not a Thread safe
        stringBufferMethod(); //Thread safe


    }
}
