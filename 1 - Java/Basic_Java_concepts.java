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

    public static void main(String [] args){

        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int []arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
        }
        print(arr,n);

    }
}
