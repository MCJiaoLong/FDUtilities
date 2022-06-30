@file:Suppress("DEPRECATION")

import org.jetbrains.kotlin.util.capitalizeDecapitalize.capitalizeAsciiOnly

plugins {
    id("org.gradle.java")
    id("org.gradle.maven-publish")
    id("org.jetbrains.kotlin.jvm") version "1.6.21" apply false
}

subprojects {
    apply<JavaPlugin>()
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }
    dependencies {
        "compileOnly"(kotlin("stdlib"))
        compileOnly("com.google.guava:guava:21.0")
    }
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.tabooproject.org/repository/releases")
            credentials {
                username = project.findProperty("taboolibUsername").toString()
                password = project.findProperty("taboolibPassword").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            groupId = project.group.toString()
        }
    }
}


tasks.jar {
    onlyIf { false }
}

tasks.build {
    doLast {
        copyOutJar(":project:api")
        copyOutJar(":project:bukkit")
        copyOutJar(":plugin")
        copyOutJar(":module:TestModule")
        //测试用
        //val file = file("$buildDir/libs").listFiles()?.find { it.endsWith("${rootProject.name}-$version.jar") }
        //file?.copyTo(file("E:\\TestServer\\Server-1.17.1\\plugins\\FDUtilities-Plugin.jar"), true)
    }
    dependsOn(
        project(":project:api").tasks.build,
        project(":project:bukkit").tasks.build,
        project(":plugin").tasks.build
    )
}

fun copyOutJar(pj: String) {
    val plugin = project(pj)
    val file = file("${plugin.buildDir}/libs").listFiles()?.find { it.endsWith("${plugin.name}-${plugin.version}.jar") }

    val jarName =
        "${rootProject.name}${if (pj == ":plugin") "" else "-${plugin.name.capitalizeAsciiOnly()}"}-${plugin.version}.jar"

    file?.copyTo(file("$buildDir/libs/${jarName}"), true)
}