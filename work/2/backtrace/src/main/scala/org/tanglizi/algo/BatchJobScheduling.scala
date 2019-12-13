package org.tanglizi.algo

class BatchJobScheduling(val cost: Array[Array[Int]], val n: Int) {
  var minCost: Int = Int.MaxValue
  var bestSchedule: Array[Int] = Array()

  def solve(): Int ={
    backtrace((0 until n).toArray, 0, 0, 0, 0)
    minCost
  }

  private[this] def backtrace(schedule: Array[Int], cost1: Int, cost2: Int, totalCost: Int, depth: Int): Unit = {
    if (depth == n) {
      minCost = totalCost
      bestSchedule = schedule.clone
    } else for (i <- depth until n) {
      val newCost1 = cost1 + cost(schedule(i))(0)
      val newCost2 = newCost1.max(cost2) + cost(schedule(i))(1)
      val newTotalCost = totalCost + newCost2

      if (newTotalCost < minCost) {
        swap(schedule, i, depth)
        backtrace(schedule, newCost1, newCost2, newTotalCost, depth + 1)
        swap(schedule, i, depth)
      }
    }
  }

  def swap(xs: Array[Int], i: Int, j: Int): Unit = {
    val tmp = xs(i)
    xs(i) = xs(j)
    xs(j) = tmp
  }
}
