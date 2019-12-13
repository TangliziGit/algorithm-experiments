package org.tanglizi.algo

class InterestingBigNumber {
  private[this] var answer: Array[Int] = Array()

  def solve(): Array[Int] ={
    def find(num: Array[Int]): Unit = {
      if (greater(num, answer)) answer = num.clone

      val start = if (num.length == 0) 1 else 0
      for (i <- start to 9 if canDivide(num :+ i, num.length + 1))
        find(num :+ i)
    }

    find(Array())
    answer
  }

  def greater(xs: Array[Int], ys: Array[Int]): Boolean = (xs, ys) match {
    case (xs, ys) if xs.length != ys.length => xs.length > ys.length
    case (xs, ys) => xs.zip(ys).foldLeft(Option.empty[Boolean]){
      case (Some(true), _) => Some(true)
      case (Some(false), _) => Some(false)
      case (v, (x, y)) => if (x == y) v else Some(x>y)
    }.getOrElse(false)
  }

  def canDivide(xs: Array[Int], n: Int): Boolean =
    xs.foldLeft(0)((x, y) => (10*x + y)%n) == 0
}
