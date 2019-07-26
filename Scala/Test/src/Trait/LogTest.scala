package Trait

class LogTest extends Log {
  val maxLength = 2
  def test(info:String): Unit ={
    log(info)
  }
}
