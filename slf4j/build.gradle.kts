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

dependencies {
    implementation(project(":carambola-core"))
    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.8.0-beta4")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "enveeed.carambola"
            artifactId = "carambola-slf4j"
            version = project.version.toString()
            from(components["java"])
        }
    }
}

bintray {

    setPublications("maven")

    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "carambola"
        name = "carambola-slf4j"
        setLicenses("GPL-3.0")
        vcsUrl = "https://github.com/enveeed/carambola"

        version(delegateClosureOf<BintrayExtension.VersionConfig> {
            name = project.version.toString()
        })

    })
}