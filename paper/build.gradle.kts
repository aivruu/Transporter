plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.blossom)
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("Server")
    }
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}

blossom {
     val route = "src/main/java/me/qeklydev/transporter/Constants.java"
     replaceToken("{VERSION}", project.version, route)
}

dependencies {
    implementation(project(":transporter-api"))

    compileOnly(libs.paper)
    compileOnly(libs.command)
}
