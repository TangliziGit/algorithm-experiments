package org.tanglizi.algo

class ExhibitionHall(val width: Int, val height: Int) {
  var minCost: Int = Int.MaxValue
  var bestMap: Array[Array[Boolean]] = Array.ofDim[Boolean](height, width)

  def solve(): Int ={
    def backtrace(iter: Int, cost: Int, map: Array[Array[Boolean]], coverage: Array[Array[Boolean]]): Unit = {
      if (iter == width * height -1 && minCost > cost && checkValid(coverage)){
        minCost = cost
        bestMap = map
      } else if (iter == width*height) return

      val (x, y) = (iter % width, iter / width)
      if (y==0 || coverage(y-1)(x))
        backtrace(iter+1, cost, map, coverage)
      val (newMap, newCoverage) = addMonitor(x, y, map, coverage)
      backtrace(iter+1, cost+1, newMap, newCoverage)
    }

    val map = Array.fill[Boolean](height, width)(false)
    val coverage = Array.fill[Boolean](height, width)(false)
    backtrace(0, 0, map, coverage)
    minCost
  }

  private[this] def checkValid(array: Array[Array[Boolean]]): Boolean =
    array.reverse.take(2).flatten.count(!_) == 0

  private[this] def addMonitor(x: Int, y: Int, map: Array[Array[Boolean]], coverage: Array[Array[Boolean]]) = {
    val (newMap, newCoverage) = (map.map(_.clone), coverage.map(_.clone))
    newMap(y)(x) = true
    for (dir <- ExhibitionHall.dirs if (y+dir._1 >= 0 && x+dir._2 >= 0 && y+dir._1 < height && x+dir._2 < width))
      newCoverage(y+dir._1)(x+dir._2) = true
    (newMap, newCoverage)
  }

}

object ExhibitionHall {
  val dirs: List[(Int, Int)] = List((0, 0), (0, 1), (1, 0), (-1, 0), (0, -1))
}
