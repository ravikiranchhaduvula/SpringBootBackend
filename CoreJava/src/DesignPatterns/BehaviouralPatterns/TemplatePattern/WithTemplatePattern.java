package DesignPatterns.BehaviouralPatterns.TemplatePattern;

abstract class DataParser {
    // Template Method Define the steps of the algorithm
    public final void parse() {
        openFile();
        closeFile();
        parseData();
    }
    protected void openFile() {
        System.out.println("Opening File");
    }

    protected void closeFile() {
        System.out.println("Closing File");
    }

    protected abstract void parseData();
}

//CSV Parser
class CSVParser2 extends DataParser {

    @Override
    protected void parseData() {
        System.out.println("Parsing CSV Data");
    }
}

//JSON Parser
class JSONParser2 extends DataParser {

    @Override
    protected void parseData() {
        System.out.println("Parsing JSON Data");
    }
}


public class WithTemplatePattern {
    public static void main(String[] args) {
        DataParser csvParser = new CSVParser2();
        DataParser jsonParser = new JSONParser2();

        csvParser.parse();
        jsonParser.parse();
    }
}
