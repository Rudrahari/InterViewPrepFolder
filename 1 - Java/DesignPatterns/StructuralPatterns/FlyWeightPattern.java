package DesignPatterns.StructuralPatterns;

import java.util.HashMap;
import java.util.Map;

// intrinsic
class DigitalBook {
    String bookName;

    public DigitalBook(String bookName) {
        this.bookName = bookName;
    }

    public void getContents() {
        System.out.println("Book - " + bookName);
    }
}

// extrinsic
class BookMarkedBook {
    String user;
    DigitalBook digitalBook;

    public BookMarkedBook(String user, DigitalBook digitalBook) {
        this.user = user;
        this.digitalBook = digitalBook;
    }

    public void getContents() {
        digitalBook.getContents();
    }
}

class BookFactory {
    static Map<String, DigitalBook> allBooks = new HashMap<>();

    public static DigitalBook getBook(String bookName) {
        DigitalBook digitalBook = allBooks.get(bookName);

        if (digitalBook == null) {
            digitalBook = new DigitalBook(bookName);
            allBooks.put(bookName, digitalBook);
            return digitalBook;
        }
        return digitalBook;
    }
}

// where there are multiple objects which would be shared by many, and share some properties
public class FlyWeightPattern {
    public static void main(String[] args) {
        DigitalBook bookARequestFromUserA = BookFactory.getBook("BookA");
        DigitalBook bookARequestFromUserB = BookFactory.getBook("BookA");
        DigitalBook bookBRequestFromUserA = BookFactory.getBook("BookB");
        DigitalBook bookBRequestFromUserB = BookFactory.getBook("BookB");
        DigitalBook bookCRequestFromUserA = BookFactory.getBook("BookC");
        DigitalBook bookCRequestFromUserB = BookFactory.getBook("BookC");

        BookMarkedBook bookMarkedBookForUserA
                =new BookMarkedBook("userA",bookARequestFromUserA);

        System.out.println(BookFactory.allBooks.size()); // 3, not 6
    }
}
