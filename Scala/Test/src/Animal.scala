/**
  * base class
  */
abstract class Animal {
  val name:String
  var age:Int

  def printInfo(): Unit ={
    println(name)
    println(age)
  }
}
