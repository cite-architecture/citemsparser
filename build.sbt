
name := "citevalidator"
organization := "edu.holycross.shot"
version := "0.0.1"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
scalaVersion := "2.11.8"

resolvers += Resolver.jcenterRepo
libraryDependencies ++=   Seq(
  "edu.holycross.shot.cite" %% "xcite" % "2.4.0",
  "net.liftweb" %% "lift-json" % "3.0.1"
)
