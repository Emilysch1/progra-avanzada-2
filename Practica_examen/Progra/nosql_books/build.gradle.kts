plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Weld SE - Dependency Injection (CDI)
    implementation("org.jboss.weld.se:weld-se-core:5.1.0.Final")

// Jandex (para anotaciones y reflexión rápida)
    implementation("io.smallrye:jandex:3.2.7")

// JSON-B (Yasson: JSON Binding)
    implementation("org.eclipse:yasson:3.0.4")

// SmallRye Config (configuración tipo MicroProfile)
    implementation("io.smallrye.config:smallrye-config:3.13.2")

// Eclipse JNoSQL - MongoDB
    implementation("org.eclipse.jnosql.databases:jnosql-mongodb:1.1.8")


    tasks.test {
        useJUnitPlatform()
    }
}