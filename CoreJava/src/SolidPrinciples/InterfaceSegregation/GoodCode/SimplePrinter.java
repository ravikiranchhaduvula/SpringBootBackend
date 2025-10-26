package SolidPrinciples.InterfaceSegregation.GoodCode;

import SolidPrinciples.InterfaceSegregation.BadCode.Document;

public class SimplePrinter implements Printer {
    @Override
    public void print(Document document) {
        System.out.println("Printing "+document);
    }
}
