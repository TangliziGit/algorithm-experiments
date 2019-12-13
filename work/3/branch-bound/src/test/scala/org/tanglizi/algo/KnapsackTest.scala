package org.tanglizi.algo

import org.junit._
import Assert._

@Test
class KnapsackTest {

    @Test
    def testSolve(): Unit = {
        val values = Array(40, 42, 25, 12)
        val weights = Array(4, 7, 5, 3)

        val knapsack = new Knapsack(values, weights, 10, 4)
        val ans = knapsack.solve()

        assertEquals(65, ans)
    }

}


