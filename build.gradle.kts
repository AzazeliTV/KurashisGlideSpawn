import net.ltgt.gradle.errorprone.errorprone

plugins {
    id("java-library")
    alias(libs.plugins.shadow)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.errorprone)
    alias(libs.plugins.pitest)
    alias(libs.plugins.dependencycheck)
}

group = "de.kurashi"
version = "1.0.0"
description = "Spawn-Insel mit automatischem Gleiten und Boost"

repositories {
    mavenCentral()
    maven {
        name = "hytale"
        url = uri("https://maven.hytale.com/release")
    }
}

dependencies {
    compileOnly(libs.hytale.server)
    compileOnly(fileTree("../kurashi_lib/build/libs/") { include("KurashiLib-*.jar") })
    // gson kommt via KurashiLib (shaded). KEIN erneutes implementation gson — sonst
    // ClassLoader-Konflikt zur Laufzeit.

    // Error Prone + NullAway
    errorprone(libs.errorprone.core)
    errorprone(libs.nullaway)
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release = 25
        // Error Prone + NullAway
        options.errorprone {
            disableWarningsInGeneratedCode.set(true)
            allErrorsAsWarnings.set(true)
            option("NullAway:AnnotatedPackages", "de.kurashi")
        }
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()

        val props = mapOf(
            "version" to project.version,
            "description" to project.description
        )
        inputs.properties(props)

        filesMatching("manifest.json") {
            expand(props)
        }
        filesMatching("version.properties") {
            expand(props)
        }
    }

    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        minimize()
    }

    build {
        dependsOn(shadowJar)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

// Error Prone: Nicht auf Tests anwenden
tasks.named<JavaCompile>("compileTestJava") {
    options.errorprone {
        enabled = false
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", rootProject.name)
        property("sonar.host.url", "http://172.17.0.2:9000")
        property("sonar.java.source", "25")
    }
}


// Sonar braucht test + (falls vorhanden) jacocoTestReport vor sich selbst
tasks.findByName("sonar")?.dependsOn(
    listOfNotNull(tasks.findByName("test"), tasks.findByName("jacocoTestReport"))
)
