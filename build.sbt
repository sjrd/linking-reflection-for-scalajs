
val commonSettings: Seq[Setting[_]] = Seq(
  version := "0.0.1-SNAPSHOT",

  scalacOptions ++= Seq("-deprecation", "-feature", "-encoding", "utf-8")
)

lazy val `sbt-scalajs-reflection` = project.in(file("sbt-scalajs-reflection")).
  settings(commonSettings: _*).
  settings(
    sbtPlugin := true,
    addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.6")
  )

lazy val `scalajs-reflection` = project.in(file(".")).
  enablePlugins(ScalaJSPlugin).
  enablePlugins(ScalaJSReflectionPlugin).
  enablePlugins(ScalaJSJUnitPlugin).
  settings(
    scalaVersion := "2.11.7"
  ).
  settings(inConfig(Test)(Seq(
    scalaJSOptimizerOptions ~= { _.withCheckScalaJSIR(true) },

    scalaJSReflectSelectors ++= Seq(
      // ReflectionTest.getClassForName()
      selectSingleClass("linkingreflection.ExactGetClassForName") -> reflectClassByName(),
      selectDescendentClasses("linkingreflection.GetClassForNameAncestor") -> reflectClassByName(),
      
      // AkkaLikeReflectionTest.getClassFor()
      selectSingleClass("linkingreflection.AkkaGetClassForName") -> reflectClassByName(),
      
      // AkkaLikeReflectionTest.createInstanceFor()
      selectSingleClass("linkingreflection.AkkaSomeConstructible") -> reflectDeclaredConstructors(),
      
      // AkkaLikeReflectionTest.getObjectFor()
      selectSingleClass("linkingreflection.AkkaGetObjectFor$") -> reflectClassByName(),
      selectSingleClass("linkingreflection.AkkaGetObjectFor$") -> reflectModuleAccessor()
    )
  )))
