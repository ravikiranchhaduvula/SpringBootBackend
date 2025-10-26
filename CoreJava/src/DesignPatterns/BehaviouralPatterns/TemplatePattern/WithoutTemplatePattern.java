package DesignPatterns.BehaviouralPatterns.TemplatePattern;

class CsvParser {
    public void parse() {
        openFile(); // Position is part of the template
        //CSV specific parsing method
        System.out.println("Parsing a CSV File"); // Plug-in
        closeFile(); // Position is part of the template
    }

    private void openFile() {
        System.out.println("Opening File");
    }

    private void closeFile() {
        System.out.println("Closing File");
    }
}

class JsonParser {
    public void parse() {
        openFile(); // Position is part of the template
        //CSV specific parsing method
        System.out.println("Parsing a JSON File"); // Plug-in
        closeFile(); // Position is part of the template
    }

    private void openFile() {
        System.out.println("Opening File");
    }

    private void closeFile() {
        System.out.println("Closing File");
    }
}

public class WithoutTemplatePattern {
    public static void main(String[] args) {
      CsvParser csvParser = new CsvParser();
      csvParser.parse();
      JsonParser jsonParser = new JsonParser();
      jsonParser.parse();
    }
}
