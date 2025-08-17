plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.ehcache:ehcache")
    implementation("org.hibernate.orm:hibernate-jcache")
    implementation("org.redisson:redisson-hibernate-6:3.27.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flowable:flowable-spring-boot-starter-process:7.0.0")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.kafka:spring-kafka")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    testCompileOnly("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
