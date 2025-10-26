package DesignPatterns.BehaviouralPatterns.IteratorPattern;

import java.util.Iterator;

public class ClientV2 {
    public static void main(String[] args) {
        BookCollectionV2 collection = new BookCollectionV2();
        collection.addBook(new Book("C++ Book"));
        collection.addBook(new Book("Java Book"));
        collection.addBook(new Book("Python Book"));

        Iterator<Book> iterator = collection.createIterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("Book Title: "+book.getTitle());
        }
    }
}
