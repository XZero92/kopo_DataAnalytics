plugins {
    id("java")
}

group = "kr.co.tlf.ex"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.oracle.database.jdbc:ojdbc11:23.8.0.25.04")
}

tasks.test {
    useJUnitPlatform()
}