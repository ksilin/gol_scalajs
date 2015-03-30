package example

import scala.scalajs.js
import org.scalajs.dom

object GoLApp extends js.JSApp {
  def main(): Unit = {

    val (h, w) = (Page.canvas.height, Page.canvas.width)
    val tile = 10
    val mag = 1/tile
    val tilesX = w/tile
    val tilesY = h/tile

    var run = true

    dom.onkeyup = (e: dom.KeyboardEvent) => {
      if(e.keyCode == 32)
        run = !run
        dom.console.log(if (run) "running" else "paused")
    }

//    dom.onmouseover = (e: dom.MouseEvent) => {
//      dom.console.log(e)
//    }

    def createRandomCells = {
      val rnd = new scala.util.Random
      val locations = (0 to (tilesX*tilesY)).toList.map{(_) => (rnd.nextInt(tilesX.toInt), rnd.nextInt(tilesY.toInt))}
      Set(locations.toSeq: _*)
    }

    var cells: Set[(Int, Int)] = createRandomCells// Set((1, 1), (1, 2), (2, 2), (3, 3), (4, 4), (5, 6), (12, 15), (3, 6), (3, 7), (3, 8))

    Page.clear()
    dom.setInterval(() => {
        val (deceased, born) = if (run) GoL.tick(cells) else (Set(), Set())
        //      dom.console.log(s"cells before: $cells")
        cells = cells -- deceased ++ born
        //      dom.console.log(s"cells after: $cells")

        //      renderDiff(born, deceased)
        Renderer.renderFull(cells, tile)
    }, 100)


  }
}
