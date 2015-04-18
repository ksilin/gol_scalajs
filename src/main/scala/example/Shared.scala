package example

object Shared {
  val url = "http://localhost:8080"
}

trait Api {
  def fastOpt(txt: String): (String, Option[String])

  def fullOpt(txt: String): (String, Option[String])

  def export(compiled: String, source: String): String

  //  def `import`(compiled: String, source: String): String

  def completeStuff(txt: String, flag: String, offset: Int): List[(String, String)]
}
