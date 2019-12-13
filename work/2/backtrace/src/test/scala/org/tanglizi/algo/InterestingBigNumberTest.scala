package org.tanglizi.algo

import java.util

import org.junit.Test
import org.junit.Assert._

@Test
class InterestingBigNumberTest {

  @Test
  def testSolve(): Unit ={
    val bigNum = new InterestingBigNumber()
    val ans = bigNum.solve()

    assertEquals("3608528850368400786036725", ans mkString "")
  }

  @Test
  def testSolveInJava(): Unit ={
    val bigNum = new InterestingBigNumberInJava()
    val ans = bigNum.solve().toArray()

    assertEquals("3608528850368400786036725", ans mkString "")
  }

  @Test
  def testDivide(): Unit = {
    val bigNumb = new InterestingBigNumberInJava()
    val x = Array(3,6,0,8,5,2,8,8,5,0,3,6,8,4,0,0,7,8,6,0,3,6,7,2,5)
    val list = new util.LinkedList[Integer]()

    for (i <- 1 to x.length) {
      list.add(x(i-1))
      println(i + " " + bigNumb.canDivide(list, i))
    }
  }
}
