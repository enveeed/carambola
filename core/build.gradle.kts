import com.jfrog.bintray.gradle.BintrayExtension

/*
 * Copyright (c) 2019 Arthur Schüler / enveeed (https://github.com/enveeed)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

plugins {
    id("java")
    id("idea")
    id("java-library")
    id("maven-publish")
    id("com.jfrog.bintray")
}

dependencies {}

// ===

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "enveeed.carambola"
            artifactId = "carambola-core"
            version = project.version.toString()

            from(components["java"])
        }
    }
}

bintray {

    user = (if(project.hasProperty("bintrayUser")) project.property("bintrayUser") else System.getenv("BINTRAY_USER")).toString()
    key = (if(project.hasProperty("bintrayKey")) project.property("bintrayKey") else System.getenv("BINTRAY_KEY")).toString()

    setPublications("maven")

    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "carambola"
        name = "carambola-core"
        setLicenses("GPL-3.0")
        vcsUrl = "https://github.com/enveeed/carambola"

        version(delegateClosureOf<BintrayExtension.VersionConfig> {
            name = project.version.toString()
        })
    })
}