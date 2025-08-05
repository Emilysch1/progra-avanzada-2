plugins {
    id("java")
    id("io.freefair.lombok") version "8.10.2"
}

group = "com.programacion"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    //Contenedor CDI: Weld
    implementation("org.jboss.weld.se:weld-se-core:5.1.3.Final")
    implementation("io.smallrye:jandex:3.2.3")

    implementation("org.hibernate:hibernate-core:6.6.4.Final")
    implementation("org.postgresql:postgresql:42.7.5")

    //DeltaSpike: plugins CDI
    implementation("org.apache.deltaspike.modules:deltaspike-jpa-module-api:2.0.0")
    runtimeOnly("org.apache.deltaspike.modules:deltaspike-jpa-module-impl:2.0.0")

    implementation("org.apache.deltaspike.modules:deltaspike-data-module-api:2.0.0")
    runtimeOnly("org.apache.deltaspike.modules:deltaspike-data-module-impl:2.0.0")

    // https://mvnrepository.com/artifact/com.github.ben-manes.caffeine/caffeine
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")

}

sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}