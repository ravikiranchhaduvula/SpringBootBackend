package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "myapp")
// Binds Values from application.yaml or application.properties to a Java object
// Looks for any config under myapp in the current environment (profile), spring merges all matching files
public class AppConfig {
    private String name;
    private List<String> features;
}
// Every Pom has a parent pom / super pom All dependencies will be inherited by child
// Maven looks for project dependencies in repository tag
// Declare dependencies are present in <dependencies> section
// mvn compile will set all files into target folder
// Settings.xml has the .m2/repository path where the jar / war will be saved