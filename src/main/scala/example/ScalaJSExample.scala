package example

import org.scalajs.dom.html

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom

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

    dom.setInterval(() => {
      Page.clear()
      Page.renderer.fillStyle = s"rgb(80, 180, 10)"
      cells = GoL.tick(cells)

//      dom.console.log(s"cells: $cells")

      for(cell <- cells){
        Page.renderer.fillRect(cell._1*tile, cell._2*tile, tile - 1, tile - 1)
      }
    }, 200)
  }

  /** Computes the square of an integer.
   *  This demonstrates unit testing.
   */
  def square(x: Int): Int = x*x
}
