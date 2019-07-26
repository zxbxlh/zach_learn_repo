package Trait

//define a base trait
trait Log{

  def log(info:String):Unit={}

  //abstract
  val maxLength:Int

  def info(info:String): Unit ={
    log("Info: "+info)
  }
}
