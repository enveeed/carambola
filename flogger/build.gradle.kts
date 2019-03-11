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
    implementation(group = "com.google.flogger", name = "flogger", version = "0.3.1")
    implementation(group = "com.google.flogger", name = "flogger-system-backend", version = "0.3.1")
    
    // TODO flogger-system-backend dependency is only required for StackBasedCallerFinder, replace this with an internal version
}