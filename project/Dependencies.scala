import sbt._

object Dependencies {
  val allDependencies = Seq(
    "org.scalameta" %% "munit"      % "0.7.29" % Test,
    "com.beachape"  %% "enumeratum" % "1.7.0"
  )
}
