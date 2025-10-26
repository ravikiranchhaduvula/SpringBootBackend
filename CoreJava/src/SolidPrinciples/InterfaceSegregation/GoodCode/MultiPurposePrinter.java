package SolidPrinciples.InterfaceSegregation.GoodCode;

import SolidPrinciples.InterfaceSegregation.BadCode.Document;

public class MultiPurposePrinter implements Printer,Scanner,Copier{
    @Override
    public void copy(Document document) {
        System.out.println("Copying: "+document);
    }

    @Override
    public void print(Document document) {
        System.out.println("Printing: "+document);
    }

    @Override
    public void scan(Document document) {
        System.out.println("Scanning: "+document);

    }
}
