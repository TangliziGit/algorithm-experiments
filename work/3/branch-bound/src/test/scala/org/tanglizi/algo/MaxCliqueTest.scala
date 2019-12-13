package org.tanglizi.algo

import org.junit.Test

@Test
class MaxCliqueTest {

  @Test
  def testSolve(): Unit = {
    val matrix = Array(
      Array(0, 0, 0, 0, 0, 0),
      Array(0, 0, 1, 0, 1, 1),
      Array(0, 1, 0, 1, 0, 1),
      Array(0, 0, 1, 0, 0, 1),
      Array(0, 1, 0, 0, 0, 1),
      Array(0, 1, 1, 1, 1, 0),
    )
    val maxClique = new MaxClique(5, matrix)

    println(maxClique.solve())
  }
}
