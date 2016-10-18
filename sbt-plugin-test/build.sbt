enablePlugins(ScalaJSReflectionPlugin)
enablePlugins(ScalaJSJUnitPlugin)

crossScalaVersions := Seq("2.10.6", "2.11.7", "2.12.0-M3")
scalaVersion := "2.11.7"

libraryDependencies += "be.doeraene" %%% "scalajs-reflection" % "0.1.2-SNAPSHOT"

unmanagedSourceDirectories in Test +=
  baseDirectory.value.getParentFile / "src/test/scala"

TestSettings.testSettings
