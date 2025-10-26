package DesignPatterns.CreationalPatterns.Singleton;

public class WithoutSingletonPattern {
    public static void main(String[] args) {
        AppSettings appSettings = AppSettings.getInstance();
        AppSettings appSettings1 = AppSettings.getInstance();

        System.out.println(appSettings1.getApiKey());
        appSettings.setApiKey("82345-ABCDE"); // In-consistencies
        System.out.println(appSettings.getApiKey());

        // More memory due to multiple objects
        System.out.println(appSettings1 == appSettings);
    }
}
