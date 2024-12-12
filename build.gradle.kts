plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.kijenasa"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
	maven("https://jitpack.io")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.github.bhlangonijr:chesslib:1.3.4")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.4.0")
	implementation("io.projectreactor:reactor-core:3.7.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
