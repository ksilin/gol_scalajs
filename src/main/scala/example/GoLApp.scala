package example

import org.scalajs.dom.html

import scala.scalajs.js
import org.scalajs.dom

object GoLApp extends js.JSApp {

  def main(): Unit = {

    val (h, w) = (Page.canvas.height, Page.canvas.width)
    val TileSize = 10
    val tilesX = w / TileSize
    val tilesY = h / TileSize

    var run = true
    val FrameTimeout: Double = 30

    val spaceAction = Util.getElem[html.Paragraph]("spaceAction")

    var frame = 0
    val frameCount = Util.getElem[html.Paragraph]("frameCount")

    val createRandomCells = {
      val rnd = new scala.util.Random
      val locations = (0 to (tilesX * tilesY)).toList.map(_ => (rnd.nextInt(tilesX), rnd.nextInt(tilesY)))
      Set(locations.toSeq: _*)
    }

    var cells: Set[(Int, Int)] = createRandomCells

    Page.clear()
    dom.setInterval(() => {
      frame += 1
      frameCount.textContent = s"frame $frame"

      val (deceased, born): (Set[(Int, Int)], Set[(Int, Int)]) = if (run) GoL.tick(cells) else (Set(), Set())
      cells = cells -- deceased ++ born

      Renderer.renderDiff(born.asInstanceOf[Set[(Int, Int)]], deceased.asInstanceOf[Set[(Int, Int)]], TileSize)
      //      Renderer.renderDiff(born, deceased, TileSize)
      //      Renderer.renderFull(cells, TileSize)
    }, FrameTimeout)

    dom.onkeyup = (e: dom.KeyboardEvent) => {
      if (e.keyCode == 32)
        run = !run
      dom.console.log(if (run) "running" else "paused")
      spaceAction.textContent = if (run) "pause" else "resume"
    }

    Page.sandbox.onmouseup = (e: dom.MouseEvent) => {
      val x: Int = (e.clientX / TileSize).toInt
      val y: Int = (e.clientY / TileSize).toInt
      cells = cells ++ Set((x -> y), (x + 1 -> y), (x -> (y + 1)), (x + 1 -> (y + 1)))
    }

  }
}