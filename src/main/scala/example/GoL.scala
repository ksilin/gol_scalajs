package example

import scala.collection.immutable.IndexedSeq

object GoL {

  val neighborOffsets: IndexedSeq[(Int, Int)] = for {
    x <- (-1 to 1)
    y <- (-1 to 1)
    if (x != 0 || y != 0)
  } yield (x -> y)

  val rules = Map(3 -> Some('alive), 2 -> None).withDefaultValue(Some('dead))

  def countNeighbors(c: Set[(Int, Int)], x: Int, y: Int): Int = {
    neighborIndices(x, y).filter(c.contains(_)).length
  }

  def neighborIndices(x: Int, y: Int): IndexedSeq[(Int, Int)] = {
    neighborOffsets map { (a) => ((a._1 + x) -> (a._2 + y)) }
  }

  def nextState(cells: Set[(Int, Int)], location: (Int, Int)): Option[Symbol] = {
    rules(countNeighbors(cells, location._1, location._2))
  }

  val toLoc: ( ((Int, Int), Option[Symbol])) => (Int, Int) = (_._1)

  def tick(c: Set[(Int, Int)]): (Set[(Int, Int)], Set[(Int, Int)]) = {
    val nextStates = locationsToCheck(c).map ((loc) => (loc -> nextState(c, loc)))
    
    // TODO - what is faster, filtering (3 times) or grouping and fetching?
    val stateMap = nextStates groupBy((a:((Int, Int), Option[Symbol])) => a._2)
    val deceased = stateMap.getOrElse(Some('dead), Set()) map toLoc
    val born = stateMap.getOrElse(Some('alive), Set()) map toLoc
    (deceased, born)
  }

  // TODO - use the neighborhood of all cells - would it be more practical?
  def locationsToCheck(c: Set[(Int, Int)]): Set[(Int, Int)] = {
    val allX = c.map(_._1)
    val allY = c.map(_._2)
    (for {
      x <- (allX.min to allX.max)
      y <- (allY.min to allY.max)
    } yield (x -> y)).toSet
  }

}
