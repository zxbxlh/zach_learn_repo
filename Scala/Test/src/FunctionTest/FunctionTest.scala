package FunctionTest

class FunctionTest {

  //匿名函数
  val anonymousF = (value: Double) => 3 * value

  //高阶函数
  def highF(value: Double, f: (Double) => Double) = {
    f(value)
  }

  //柯里化：新函数返回原有第二参数作为参数的函数
  def highF2(value: Double) = (x: Double) => x * value

  def highF3(x: Double)(y: Double) = x * y

  //控制抽象
  def runInThread(block: () => Unit): Unit = {
    new Thread(new Runnable {
      override def run(): Unit = block()
    }).start()
  }

  //控制抽象1
  def runInThread2(block: => Unit): Unit ={
    new Thread(new Runnable {
      override def run(): Unit = block
    }).start()
  }

  //控制抽象2
  def until(condition: => Boolean)(block: => Unit) {
    if (!condition) {
      block
      until(condition)(block)
    }
  }

  def test(): Unit = {
    println(anonymousF(3.0))
    println(highF(4.0, anonymousF))

    //简化单参数的函数参数
    println(highF(2.0, (x: Double) => 1 + x))
    println(highF(2.0, x => 1 + x))
    println(highF(2.0, 1 + _))

    //闭包
    val closure1 = highF2(1)
    val closure2 = highF2(2)
    println(closure1(2))
    println(closure2(2))

    //柯里化
    highF2(3)(4)
    highF3(3)(4)

    //控制抽象
    runInThread(()=>{
      println(Thread.currentThread().getName())
      Thread.sleep(1000)
      println("bye")
    })

    //控制抽象1
    runInThread2({
      println(Thread.currentThread().getName())
      Thread.sleep(1000)
      println("bye")
    })
  }

  //控制抽象2
  var x = 10
  until(x==0){
    x-=1
    println(x)
  }

}


object FunctionTest {
  def apply: FunctionTest = new FunctionTest()
}
