name := """tour_4_me_2.3"""

version := "1.0-SNAPSHOT"

resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns)

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += Resolver.url("Objectify Play Repository - snapshots", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns)

scalaVersion := "2.10.4"

sbt.Keys.fork in Test := false

lazy val root = Project("computer-database-slick", file("."))
  .enablePlugins(PlayScala)
  .dependsOn(ProjectRef(file("slick"), "playSlick"))

libraryDependencies ++= Seq(
  jdbc,
    cache,
    ws,
    "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
    "com.typesafe.play" %% "play-slick" % "0.8.0",
    "net.sourceforge.htmlunit" % "htmlunit" % "2.15"
)
