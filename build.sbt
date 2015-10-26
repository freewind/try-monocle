name := "try-monocle"

version := "1.0"

resolvers += Resolver.sonatypeRepo("releases")

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.11.7" // or "2.10.6"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.2.5",
  "com.github.julien-truffaut" %% "monocle-core" % "1.2.0-M1",
  "com.github.julien-truffaut" %% "monocle-generic" % "1.2.0-M1",
  "com.github.julien-truffaut" %% "monocle-macro" % "1.2.0-M1",
  "com.github.julien-truffaut" %% "monocle-state" % "1.2.0-M1",
  "com.github.julien-truffaut" %% "monocle-law" % "1.2.0-M1" % "test",
  "com.softwaremill.quicklens" %% "quicklens" % "1.4.1"
)

// for @Lenses macro support
addCompilerPlugin("org.scalamacros" %% "paradise" % "2.0.1" cross CrossVersion.full)
