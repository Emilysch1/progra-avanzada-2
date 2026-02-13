plugins {
    id("java")
    id("io.freefair.lombok") version "8.13.1"
}

group = "com.programacion.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    //CDI
    implementation("org.jboss.weld.se:weld-se-core:6.0.2.Final")
    implementation("io.smallrye:jandex:3.2.7")

    //JPA
    implementation("org.hibernate:hibernate-core:7.0.2.Final")
    implementation("org.postgresql:postgresql:42.7.5")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.setResourcesDir(
            file("${buildDir}/classes/java/main") )
    }
}

