package DesignPatterns.BehaviouralPatterns.IteratorPattern;

public class ClientV1 {
    public static void main(String[] args) {
        BookCollection collection = new BookCollection();
        collection.addBook(new Book("C++ Book"));
        collection.addBook(new Book("Java Book"));
        collection.addBook(new Book("Python Book"));

        // If someone changes the collection in the book collection then client need to be changed
        // Which is not feasible
        for(int i=0; i<collection.getBooks().size(); i++) {
            System.out.println(collection.getBooks().get(i));
        }
    }
}
