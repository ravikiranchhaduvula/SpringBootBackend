package BasicUML;

import javax.print.Doc;

class Document {
    private String content;

    public Document(String content) {
       this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class Printer {
    public void print(Document document) {
        System.out.println("Printing Document: "+ document.getContent());
    }
}

public class Dependency {
    public static void main(String[] args) {
        Document document = new Document("Dependency Example Document");
        Printer printer = new Printer();
        //Printer depends on document to print the contents
        printer.print(document);
    }
}
