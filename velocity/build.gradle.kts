plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.blossom)
    alias(libs.plugins.velocity)
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("Proxy")

        relocate("net.byteflux.libby", "me.qeklydev.transporter.libs.net.byteflux.libby")
    }
    runVelocity {
        velocityVersion(libs.versions.velocity.get())
    }
}

blossom {
     val route = "src/main/java/me/qeklydev/transporter/Constants.java"
     replaceToken("{VERSION}", project.version, route)
}

dependencies {
    implementation(project(":transporter-api"))

    compileOnly(libs.velocity)

    implementation(libs.libby.velocity)
}
