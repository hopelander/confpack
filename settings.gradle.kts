rootProject.name = "confpack"

pluginManagement {
    plugins {
        val openapiVersion: String by settings
        id("org.openapi.generator") version openapiVersion apply false
    }
}

include("confpack-api-v1-jackson")
