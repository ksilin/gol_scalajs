package example

import org.scalajs.dom.html

import scala.scalajs.js
import org.scalajs.dom

object GoLApp extends js.JSApp {
  def main(): Unit = {

    val (h, w) = (Page.canvas.height, Page.canvas.width)
    val tileSize = 10
    val tilesX = w / tileSize
    val tilesY = h / tileSize

    var run = true
    val spaceAction = Util.getElem[html.Paragraph]("spaceAction")

    var frame = 0
    val frameCount = Util.getElem[html.Paragraph]("frameCount")

    val createRandomCells = {
      val rnd = new scala.util.Random
      val locations = (0 to (tilesX * tilesY)).toList.map(_ => (rnd.nextInt(tilesX.toInt), rnd.nextInt(tilesY.toInt)))
      Set(locations.toSeq: _*)
    }

    var cells: Set[(Int, Int)] = createRandomCells

    Page.clear()
    dom.setInterval(() => {
      frame += 1
      frameCount.textContent = s"frame $frame"

      val (deceased, born) = if (run) GoL.tick(cells) else (Set(), Set())
      cells = cells -- deceased ++ born

      Renderer.renderDiff(born.asInstanceOf[Set[(Int, Int)]], deceased.asInstanceOf[Set[(Int, Int)]], tileSize)
      //      Renderer.renderDiff(born, deceased, tileSize)
      //      Renderer.renderFull(cells, tileSize)
    }, 30)

    dom.onkeyup = (e: dom.KeyboardEvent) => {
      if (e.keyCode == 32)
        run = !run
      dom.console.log(if (run) "running" else "paused")
      spaceAction.textContent = if (run) "pause" else "resume"
    }

    Page.sandbox.onmouseup = (e: dom.MouseEvent) => {
      val x: Int = (e.clientX / tileSize).toInt
      val y: Int = (e.clientY / tileSize).toInt
      cells = cells ++ Set((x -> y), (x + 1 -> y), (x -> (y + 1)), (x + 1 -> (y + 1)))
    }

  }
}