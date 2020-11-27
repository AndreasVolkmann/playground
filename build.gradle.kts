import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.4.20"
	id("org.springframework.boot") version "2.3.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("com.github.ben-manes.versions") version "0.36.0"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
}

group = "me.avo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8
val javaVersion = java.sourceCompatibility.majorVersion
val coroutinesVersion = "1.4.1"
val aspectJVersion = "1.9.6"

repositories {
	jcenter()
	mavenCentral()
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.microsoft.azure:azure-cosmosdb-spring-boot-starter:2.3.5")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${coroutinesVersion}")
	implementation("org.aspectj:aspectjweaver:$aspectJVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.strikt:strikt-spring:0.28.0")
	testImplementation("com.tngtech.archunit:archunit:0.14.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict", "-Xinline-classes")
		jvmTarget = "1.8"
		useIR = true
	}
}

val aspectJPath: String = project.configurations.compileClasspath.get()
	.find { it.name.startsWith("aspectjweaver") }
	?.path ?: throw IllegalArgumentException("AspectJ weaver is not found on the compile classpath.")

tasks.withType<Test>().configureEach {
	jvmArgs("-javaagent:${aspectJPath}")
}