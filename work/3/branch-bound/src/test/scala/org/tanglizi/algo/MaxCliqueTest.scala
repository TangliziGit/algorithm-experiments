package org.tanglizi.algo

import org.junit.Test
import org.junit.Assert._

@Test
class MaxCliqueTest {
  val matrix = Array(
    Array(0, 1, 0, 1, 1),
    Array(1, 0, 1, 0, 1),
    Array(0, 1, 0, 0, 1),
    Array(1, 0, 0, 0, 1),
    Array(1, 1, 1, 1, 0),
  )

  @Test
  def testSolve(): Unit = {
    val maxClique = new MaxClique(5, matrix)
    val ans = maxClique.solve()

    println(ans)
    assertEquals(3, ans.size)
  }
}
