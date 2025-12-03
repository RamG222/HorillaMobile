import org.gradle.api.file.Directory
import org.gradle.api.tasks.Delete

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Put all Gradle build outputs under /build at project root
val newBuildDir: Directory =
    rootProject.layout.buildDirectory
        .dir("../../build")
        .get()

rootProject.layout.buildDirectory.value(newBuildDir)

// Make each subproject (like :app) use its own subfolder inside that root build dir
subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

// Ensure :app is evaluated first (Flutter convention)
subprojects {
    project.evaluationDependsOn(":app")
}

// clean task for `./gradlew clean`
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
