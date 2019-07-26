
import scala.collection.mutable.ArrayBuffer

object Main {

  //define a method,passed a function as parameter
  def testFun(f: (Int, Int) => Int): Int = {
    f(2, 6)
  }


  //define function 1
  val f1 = (x: Int, y: Int) => x + y
  //def function 2
  val f2 = (x: Int, y: Int) => x * y


  //test array
  def arrayTest(): Unit = {
    //like java array
    val array: Array[Int] = new Array[Int](5);
    //like java ArrayList
    val arrayBuffer = new ArrayBuffer[Int]();
    arrayBuffer += 1
    arrayBuffer += 2
    arrayBuffer += (1, 2, 3)
    arrayBuffer ++= Array(5, 6, 7)

    for (item <- 0 until arrayBuffer.length)
      println("item: " + item)

    val newArray = for (item <- arrayBuffer if item % 2 == 0) yield item + 1
    val newArray1 = arrayBuffer.filter(_ % 2 == 0).map(2 * _)
    println(newArray)
    println(newArray1)

    //switch array adjacent element
    val testArray = Array(1, 2, 3, 4, 5)

    val newTestArray = for (i <- 0 until testArray.length) yield {
      if (i % 2 == 0) if (i != testArray.length - 1) testArray(i + 1) else testArray(testArray.length - 1)
      else testArray(i - 1)
    }

    println(newTestArray.toBuffer)
  }


  //test map
  def testMap(): Unit = {
    //元组
    val array = Array("<", "-", ">")
    val count = Array(2, 1, 0)
    val pairs = array.zip(count)
    println(pairs.toBuffer)

    for ((k, v) <- pairs) println(k * v)
  }


  def main(args: Array[String]): Unit = {
    Eighteen.EighteenTest.test()
  }
}