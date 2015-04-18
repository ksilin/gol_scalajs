package example

object Renderer {

  def renderDiff(born: Set[(Int, Int)], deceased: Set[(Int, Int)], tileSize: Int): Unit = {
    Page.renderer.fillStyle = "rgb(0, 0, 0)"
    for (cell <- deceased) {
      Page.renderer.fillRect(cell._1 * tileSize, cell._2 * tileSize, tileSize - 1, tileSize - 1)
    }
    Page.renderer.fillStyle = "rgb(80, 180, 10)"
    for (cell <- born) {
      Page.renderer.fillRect(cell._1 * tileSize, cell._2 * tileSize, tileSize - 1, tileSize - 1)
    }
  }

  def renderFull(cells: Set[(Int, Int)], tile: Double): Unit = {
    Page.clear()
    Page.renderer.fillStyle = "rgb(80, 180, 10)"
    for (cell <- cells) {
      Page.renderer.fillRect(cell._1 * tile, cell._2 * tile, tile - 1, tile - 1)
    }
  }

}
