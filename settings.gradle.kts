@file:Suppress("UnstableApiUsage")

rootProject.name = "Transporter"

/*
 * Inclusion settings for common subprojects.
 */
arrayOf("api", "paper", "velocity").forEach {
    val project = ":transporter-$it"
    include(project)
    project(project).projectDir = file(it)
}
