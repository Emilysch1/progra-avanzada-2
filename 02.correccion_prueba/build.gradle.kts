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
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.1.0")
    implementation("org.apache.openwebbeans:openwebbeans-se:4.0.3")

    //JPA
    //implementation("org.eclipse.persistence:eclipselink:4.0.7")
    //implementation("org.eclipse.persistence:org.eclipse.persistence.jpa:4.0.7")
    implementation("org.hibernate:hibernate-core:7.0.2.Final")
    implementation("org.postgresql:postgresql:42.7.5")

    //Extensiones CDI: DeltaSpike
    implementation("org.apache.deltaspike.modules:deltaspike-jpa-module-api:2.0.0")
    runtimeOnly("org.apache.deltaspike.modules:deltaspike-jpa-module-impl:2.0.0")

    implementation("org.apache.deltaspike.modules:deltaspike-data-module-api:2.0.0")
    runtimeOnly("org.apache.deltaspike.modules:deltaspike-data-module-impl:2.0.0")
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

