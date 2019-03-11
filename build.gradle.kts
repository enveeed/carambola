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
    id("com.jfrog.bintray") version "1.8.3"
}

version = "0.0.1-beta"
group = "carambola"

subprojects {

    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.jfrog.bintray")

    //

    version = rootProject.version
    group = rootProject.group
    
    //
    
    repositories {
        mavenCentral()
        jcenter()
    }

    //

    bintray {

        user = (if(project.hasProperty("bintrayUser")) project.property("bintrayUser") else System.getenv("BINTRAY_USER")).toString()
        key = (if(project.hasProperty("bintrayKey")) project.property("bintrayKey") else System.getenv("BINTRAY_KEY")).toString()
    }
}