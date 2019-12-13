package org.tanglizi.algo

import org.junit.Test
import org.junit.Assert._

@Test
class BatchJobSchedulingTest {

  @Test
  def testSolve(): Unit ={
    val n: Int = 3
    val cost: Array[Array[Int]] = Array(
      Array(2, 1), Array(3, 1), Array(2, 3)
    )

    val scheduling = new BatchJobScheduling(cost, n)
    val minCost = scheduling.solve()
    val bestScheduling = scheduling.bestSchedule

    assertEquals(18, minCost)
    assertArrayEquals(Array(0, 2, 1), bestScheduling)

    println(s"minCost: $minCost\nbestScheduling: [${bestScheduling.mkString(", ")}]")
  }
}
