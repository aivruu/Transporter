plugins {
    `maven-publish`
}

publishing {
	  publications {
	      create<MavenPublication>("maven") {
	          from(components["java"])
	      }
	  }
}

dependencies {
    compileOnly(libs.configurate)
}
