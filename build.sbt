ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.0"
ThisBuild / organization := "io.matematyk60"
ThisBuild / organizationName := "matematyk60"

lazy val root = (project in file("."))
  .settings(
    name := "rpn",
    libraryDependencies ++= Dependencies.allDependencies
  )
