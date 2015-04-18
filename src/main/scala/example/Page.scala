package example

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom
import dom.html
import scalatags.JsDom.all._

/**
 * API for things that belong to the page, and are useful to both the fiddle
 * client, user code as well as exported read-only pages.
 */
@JSExport
object Page {

  val DefaultWidth = 1000
  val DefaultHeight = 600
  val FillRectDim = 10000

  val fiddleUrl = Shared.url

  lazy val red = span(color := "#E95065")
  lazy val blue = span(color := "#46BDDF")
  lazy val green = span(color := "#52D273")
  lazy val yellow = span(color := "#E5C453")
  lazy val orange = span(color := "#E57255")

  def sandbox: html.Div = Util.getElem[html.Div]("playground")

  def canvas: html.Canvas = Util.getElem[html.Canvas]("canvas")

  def renderer: dom.CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  def clear(): Unit = {
    canvas.width = DefaultWidth
    canvas.height = DefaultHeight
    val tmp = renderer.fillStyle
    renderer.fillStyle = "rgb(0, 0, 0)"
    renderer.fillRect(0, 0, FillRectDim, FillRectDim)
    renderer.fillStyle = tmp
  }

  def scroll(px: Int): Unit = {
    dom.console.log("Scrolling", px)
  }

  @JSExport
  def exportMain(): Unit = {
    dom.console.log("exportMain")
    clear()
  }

}
