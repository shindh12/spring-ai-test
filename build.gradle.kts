import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

group = "com.sdh"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/snapshot") }
}
val springAiVersion by extra { "0.8.1-SNAPSHOT" }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(platform("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}"))
    // Replace the following with the starter dependencies of specific modules you wish to use
    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0") // for web mvc swagger
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")

    implementation("io.github.microutils:kotlin-logging:3.0.5") // logging

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}