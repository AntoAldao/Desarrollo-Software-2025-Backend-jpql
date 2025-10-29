plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Hibernate JPA dependencies
    implementation("org.hibernate:hibernate-core:5.6.10.Final")
    implementation("org.hibernate:hibernate-envers:5.6.10.Final")
    implementation("org.hibernate:hibernate-entitymanager:5.6.10.Final")
    
    // Lombok
    implementation("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    
    // Jackson for JSON processing
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    
    // H2 Database for development
    runtimeOnly("com.h2database:h2:1.4.200")
    
    // JUnit for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}