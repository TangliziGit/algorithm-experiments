package org.tanglizi.algo

import org.junit.Test
import org.junit.Assert._

@Test
class ExhibitionHallTest {

  @Test
  def testSolve(): Unit ={
    implicit def bool2int(b: Boolean) = if (b) 1 else 0

    val hall = new ExhibitionHall(4, 4)
    val cost = hall.solve()
    val answer = hall.bestMap

    assertEquals(4, cost)

    println(s"cost $cost")
    for (xs <- answer) println(s"${xs.map(x => x: Int).mkString(", ")}")
  }
}
