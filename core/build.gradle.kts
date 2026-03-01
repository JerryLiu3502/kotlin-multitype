plugins {
    kotlin("jvm")
    `maven-publish`
}

version = "1.0.0"

group = "com.multitype"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.22")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.22")
    testImplementation("junit:junit:4.13.2")
}

tasks.test {
    useJUnit()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.multitype"
            artifactId = "core"
            version = "1.0.0"
            
            from(components["kotlin"])
            
            pom {
                name.set("kotlin-multitype")
                description.set("A powerful and flexible Kotlin multi-type RecyclerView adapter library")
                url.set("https://github.com/JerryLiu3502/kotlin-multitype")
                
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("JerryLiu")
                        name.set("Jerry Liu")
                        email.set("jerry@example.com")
                    }
                }
                
                scm {
                    connection.set("scm:git:https://github.com/JerryLiu3502/kotlin-multitype.git")
                    developerConnection.set("scm:git:https://github.com/JerryLiu3502/kotlin-multitype.git")
                    url.set("https://github.com/JerryLiu3502/kotlin-multitype")
                }
            }
        }
    }
}
