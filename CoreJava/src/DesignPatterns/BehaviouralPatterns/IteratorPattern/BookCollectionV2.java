package DesignPatterns.BehaviouralPatterns.IteratorPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookCollectionV2 {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Iterator<Book> createIterator() {
        return new BookIterator(this.books);
    }

    // Another class (Nested class) -- Class logic depends on upper class hence nested class
    private class BookIterator implements Iterator<Book> {

        private List<Book> books;

        private int postion = 0;

        public BookIterator(List<Book> books) {
            this.books = books;
        }
        // Reference to Data structure
        @Override
        public boolean hasNext() {
            return postion < books.size();
        }

        @Override
        public Book next() {
            return books.get(postion++);
        }
    }
}
