plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}




group = "fr.kayrouge"
version = "7"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}


dependencies {
    implementation("com.google.guava:guava:33.4.6-jre")
}

val shade: Configuration by configurations.creating
configurations.compileClasspath.get().extendsFrom(shade)

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    configurations = listOf(project.configurations.getByName("shade"))
    relocate("com.google", "hera.libs.google")
}


val generatedDir = layout.buildDirectory.dir("generated/sources/buildConfig/java/main")

sourceSets["main"].java.srcDir(generatedDir)

tasks.register("generateBuildConfig") {
    val output = generatedDir.get().asFile
    outputs.dir(output)

    doLast {
        val packageName = "fr.kayrouge.hera"
        val versionLong = project.version.toString().toLong()

        val packageDir = File(output, packageName.replace('.', '/'))
        packageDir.mkdirs()

        File(packageDir, "BuildConfig.java").writeText("""
            package $packageName;

            public class BuildConfig {
                public static final long VERSION = $versionLong;
            }
        """.trimIndent())
    }
}

tasks.named("compileJava") {
    dependsOn("generateBuildConfig")
}