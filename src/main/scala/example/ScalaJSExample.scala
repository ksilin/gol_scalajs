package example

import org.scalajs.dom.html

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom
import example.Renderer

object ScalaJSExample extends js.JSApp {
  def main(): Unit = {

    val (h, w) = (Page.canvas.height, Page.canvas.width)
    var x = 0.0
    var y = 0.0
    val tile = 20
    val mag = 1/tile
    val tilesX = w/tile
    val tilesY = h/tile

    val rnd = new scala.util.Random
    val locations = (0 to 250).toList.map{(_) => (rnd.nextInt(30), rnd.nextInt(30))}

    var cells: Set[(Int, Int)] = Set(locations.toSeq: _*)// Set((1, 1), (1, 2), (2, 2), (3, 3), (4, 4), (5, 6), (12, 15), (3, 6), (3, 7), (3, 8))

    Page.clear()

    dom.setInterval(() => {
      val (deceased, born) = GoL.tick(cells)
      cells = cells -- deceased ++ born
//      dom.console.log(s"cells: $cells")

//      renderDiff(born, deceased)
      Renderer.renderFull(cells, tile)
    }, 200)
  }
}
