import net.ltgt.gradle.errorprone.errorprone

plugins {
    id("java-library")
    id("com.gradleup.shadow") version "9.3.1"
    id("org.sonarqube") version "7.2.3.7755"
    id("net.ltgt.errorprone") version "5.1.0"
    id("info.solidsoft.pitest") version "1.19.0"
    id("org.owasp.dependencycheck") version "12.2.0"
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
    compileOnly("com.hypixel.hytale:Server:+")
    compileOnly(fileTree("../kurashi_lib/build/libs/") { include("KurashiLib-*.jar") })
    implementation("com.google.code.gson:gson:2.10.1")

    // Error Prone + NullAway
    errorprone("com.google.errorprone:error_prone_core:2.36.0")
    errorprone("com.uber.nullaway:nullaway:0.12.6")
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
        relocate("com.google.gson", "de.kurashi.glidespawn.libs.gson")
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
