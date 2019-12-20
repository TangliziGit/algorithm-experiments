package org.tanglizi.algo

import scala.collection.mutable

class MaxClique(val n: Int,
                implicit val matrix: Array[Array[Int]]) {

  private var maxClique: Set[Int] = computeLowerBound

  implicit val ordering: Ordering[State] = Ordering.by[State, Int](_.upperBound)
  private val que = mutable.PriorityQueue.empty[State]

  private def computeLowerBound: Set[Int] = n match {case 0 => Set() case _ => Set(1)}
  private def computeUpperBound(state: State): Int = state.set.size + state.rest.size

  def solve(): Set[Int] = {
    State.upperBoundComputer = this.computeUpperBound
    println(s"start with lower bound: $maxClique")

    val startNode = new State(rest = (0 until n).toSet)
    que.enqueue(startNode)

    while (que.nonEmpty) {
      val from = que.dequeue()

      for (i <- from.rest if from.check(i)) {
        val next = new State(from.set + i, from.rest - i)

        if (next.upperBound > maxClique.size) {
          que.enqueue(next)
          if (next.set.size > maxClique.size)
            maxClique = next.set
        }
      }
    }

    maxClique
  }

  object State {
    private[MaxClique] var upperBoundComputer: State => Int = _
  }

  class State(val set: Set[Int] = Set[Int](),
              val rest: Set[Int]) {
    private[MaxClique] val upperBound: Int = State.upperBoundComputer.apply(this)

    private[MaxClique] def check(node: Int)(implicit matrix: Array[Array[Int]]): Boolean =
      !set.exists(matrix(_)(node) == 0)

    override def toString: String = s"set: $set, rest: $rest"
  }

}
