package example

object Renderer {

  def renderDiff(born: Set[(Int, Int)], deceased: Set[(Int, Int)], tile: Double): Unit = {
    Page.renderer.fillStyle = s"rgb(0, 0, 0)"
    for (cell <- deceased) {
      Page.renderer.fillRect(cell._1 * tile, cell._2 * tile, tile - 1, tile - 1)
    }
    Page.renderer.fillStyle = s"rgb(80, 180, 10)"
    for (cell <- born) {
      Page.renderer.fillRect(cell._1 * tile, cell._2 * tile, tile - 1, tile - 1)
    }
  }

  def renderFull(cells: Set[(Int, Int)], tile: Double): Unit = {
    Page.clear()
    Page.renderer.fillStyle = s"rgb(80, 180, 10)"
    for (cell <- cells) {
      Page.renderer.fillRect(cell._1 * tile, cell._2 * tile, tile - 1, tile - 1)
    }
  }

}
