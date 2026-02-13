plugins {
    id("java")
    id("io.freefair.lombok") version "9.1.0"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jboss.weld.se:weld-se-core:6.0.3.Final")
    implementation("io.smallrye:jandex:3.5.1")

    implementation("org.hibernate:hibernate-core:7.1.10.Final")

    runtimeOnly("org.postgresql:postgresql:42.7.8")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.resourcesDir = file("${layout.buildDirectory.get()}/classes/java/main")
    }
}
