import org.gradle.kotlin.dsl.register
import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

fun cfg(key: String) = providers.gradleProperty(key)
fun env(key: String) = providers.environmentVariable(key)

plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.9.0"
    id("org.jetbrains.changelog") version "2.4.0"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
    kotlin("jvm") version "2.2.10"
}

group = cfg("pluginGroup").get()
version = cfg("pluginVersion").get()

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

kotlin {
    jvmToolchain(17)
}

sourceSets {
    main {
        java.srcDirs("src/main/gen")
    }
    test {
        java.srcDirs("src/main/gen")
    }
}

dependencies {
    intellijPlatform {
        create(providers.gradleProperty("platformType"), providers.gradleProperty("platformVersion"))
        plugins(providers.gradleProperty("platformPlugins").map { it.split(',') })
        testFramework(TestFrameworkType.Platform)
    }

    testImplementation("junit:junit:4.13.2")
}

intellijPlatform {
    pluginConfiguration {
        name = cfg("pluginName")
        version = cfg("pluginVersion")

        description = providers.fileContents(layout.projectDirectory.file("README.md")).asText.map {
            val start = "<!-- Plugin description -->"
            val end = "<!-- Plugin description end -->"

            with(it.lines()) {
                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end)).joinToString("\n").let(::markdownToHTML)
            }
        }

        changeNotes = provider {
            changelog.getAll().values.joinToString("\n") { changelog.renderItem(it, Changelog.OutputType.HTML) }
        }

        ideaVersion {
            sinceBuild = cfg("pluginSinceBuild")
        }
    }

    autoReload = true
}

grammarKit {
    jflexRelease.set(cfg("jflexRelease"))
    grammarKitRelease.set(cfg("grammarKitRelease"))
}

changelog {
    version = cfg("pluginVersion")
    repositoryUrl = cfg("pluginRepositoryUrl")
    path = file("CHANGELOG.md").canonicalPath
}

val generateNeonParser = tasks.register<GenerateParserTask>("generateLatteParser") {
    sourceFile.set(File("src/main/kotlin/org/nette/neon/parser/NeonParser.bnf"))
    targetRootOutputDir.set(File("src/main/gen"))
    pathToParser.set("/org/nette/neon/parser/NeonParser.kt")
    pathToPsiRoot.set("/org/nette/neon/psi")
    purgeOldFiles.set(false)
}

val generateNeonContentLexer = tasks.register<GenerateLexerTask>("generateNeonContentLexer") {
    sourceFile.set(File("src/main/kotlin/org/nette/neon/lexer/neon.flex"))
    targetOutputDir.set(File("src/main/gen/org/nette/neon/lexer"))
    purgeOldFiles.set(false)
}

tasks {
    generateLexer.configure { enabled = false }
    generateParser.configure { enabled = false }

    withType<KotlinCompile> {
        dependsOn(
            generateNeonContentLexer,
            generateNeonParser
        )
    }

    wrapper {
        gradleVersion = cfg("gradleVersion").get()
    }

    publishPlugin {
        token = env("PLUGIN_PUBLISH_TOKEN")
    }
}
