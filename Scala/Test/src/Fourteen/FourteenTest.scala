package Fourteen

import Array._

class FourteenTest extends Test {

  //swap
  def swap[S, T](tup: (S, T)) = {
    tup match {
      case (a, b) => (b, a)
    }
  }

  //swap1
  def swap1(arr: Array[String]) = {

    arr match {
      case Array(a, b, rest @_*) => {
        var newArray = Array[String](b,a)
        //newArray + rest
        newArray
      }
      case _ => arr
    }
  }

  override def test = {
    println(swap(1, 2))
    println(swap1(Array("1", "2", "3", "4")).toString)
  }
}

object FourteenTest {
  def apply: FourteenTest = new FourteenTest()
}
